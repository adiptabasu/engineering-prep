import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146: LRU Cache
 * https://leetcode.com/problems/lru-cache/
 *
 * PROBLEM
 * -------
 * Design a Least Recently Used (LRU) cache that supports:
 *
 * 1. get(key):
 *    Return the value associated with the key.
 *    Return -1 if the key does not exist.
 *    A successful access makes the entry the most recently used.
 *
 * 2. put(key, value):
 *    Insert a new key-value pair or update an existing key.
 *    If capacity is exceeded, evict the least recently used entry.
 *
 * Both operations must run in O(1) average time.
 *
 *
 * APPROACH: HASHMAP + DOUBLY LINKED LIST
 * --------------------------------------
 * HashMap<Integer, Node>:
 * - Maps each key to its corresponding Node.
 * - Provides O(1) average-time lookup, insertion, and removal.
 *
 * Doubly linked list:
 * - Maintains the usage order of cache entries.
 * - The node immediately after head is the MOST RECENTLY USED.
 * - The node immediately before tail is the LEAST RECENTLY USED.
 *
 * Dummy head and tail nodes:
 * - Eliminate special cases when inserting or removing nodes.
 * - The actual cache entries always lie between head and tail.
 *
 *
 * INVARIANTS
 * ----------
 * 1. Every real node in the linked list has exactly one corresponding
 *    entry in the HashMap.
 *
 * 2. Every entry in the HashMap points to a node in the linked list.
 *
 * 3. head.next is the most recently used node when the cache is nonempty.
 *
 * 4. tail.prev is the least recently used node when the cache is nonempty.
 *
 * 5. The number of cache entries never exceeds capacity after put() returns.
 *
 *
 * COMPLEXITY
 * ----------
 * get(key):       O(1) average time
 * put(key,value): O(1) average time
 * Space:          O(capacity)
 */
public class LRUCache {

    // Maps a cache key to its corresponding linked-list node.
    private final Map<Integer, Node> map = new HashMap<>();

    // Dummy boundary nodes; neither represents a cache entry.
    private final Node head;
    private final Node tail;

    private final int capacity;

    /**
     * Initializes an empty LRU cache.
     *
     * Initially:
     *
     * head <-> tail
     *
     * @param capacity maximum number of cache entries
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;

        this.head = new Node();
        this.tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    /**
     * Inserts an existing node immediately after head.
     *
     * This makes the node the most recently used entry.
     *
     * The node must not currently be linked into the list.
     * Call removeNode(node) first if it is already present.
     *
     * Before:
     *
     * head <-> firstNode <-> ...
     *
     * After:
     *
     * head <-> node <-> firstNode <-> ...
     *
     * Time: O(1)
     */
    private void addToFront(Node node) {

        Node firstNode = head.next;

        head.next = node;
        node.prev = head;

        firstNode.prev = node;
        node.next = firstNode;
    }

    /**
     * Removes a node from its current position in the linked list.
     *
     * IMPORTANT:
     * This method only modifies the linked list.
     * It does not remove the node from the HashMap.
     *
     * Saving the neighboring nodes before unlinking the target
     * allows us to reconnect them correctly.
     *
     * Before:
     *
     * previousNode <-> node <-> nextNode
     *
     * After:
     *
     * previousNode <-> nextNode
     *
     * The removed node's pointers are cleared to avoid stale links.
     *
     * Time: O(1)
     */
    private void removeNode(Node node) {

        Node previousNode = node.prev;
        Node nextNode = node.next;

        // Disconnect the target node.
        node.prev = null;
        node.next = null;

        // Connect its former neighbors.
        previousNode.next = nextNode;
        nextNode.prev = previousNode;
    }

    /**
     * Evicts the least recently used entry.
     *
     * The LRU entry is always tail.prev.
     *
     * Both data structures must be updated:
     * 1. Remove the key from the HashMap.
     * 2. Unlink the node from the doubly linked list.
     *
     * We save the key before removing the node so the mapping
     * can be deleted using the correct HashMap key.
     *
     * PRECONDITION:
     * The cache must contain at least one real entry.
     *
     * Time: O(1) average
     */
    private void deleteLRUEntry() {

        Node lruNode = tail.prev;

        // Remove the cache entry from the HashMap.
        map.remove(lruNode.key);

        // Remove the same entry from the linked list.
        removeNode(lruNode);
    }

    /**
     * Retrieves the value associated with key.
     *
     * If the key exists:
     * 1. Retrieve its node from the HashMap.
     * 2. Move the node to the front of the linked list.
     * 3. Return its value.
     *
     * Moving the node to the front updates its recency.
     *
     * If the key does not exist, return -1.
     *
     * Notice that a successful get() does NOT remove the key
     * from the HashMap. Only the linked-list position changes.
     *
     * Time: O(1) average
     *
     * @param key cache key
     * @return associated value, or -1 if the key is absent
     */
    public int get(int key) {

        Node targetNode = map.get(key);

        // HashMap.get() returns null when the key is absent,
        // because this implementation never stores null values.
        if (targetNode == null) {
            return -1;
        }

        // The accessed entry becomes the most recently used.
        removeNode(targetNode);
        addToFront(targetNode);

        return targetNode.value;
    }

    /**
     * Inserts a new entry or updates an existing entry.
     *
     * CASE 1: Key already exists
     * -------------------------
     * - Reuse the existing node.
     * - Update its value.
     * - Move it to the front.
     * - No HashMap insertion is needed because the mapping
     *   already points to this same node.
     *
     * CASE 2: Key does not exist
     * -------------------------
     * - Create a new node.
     * - Add it to the linked list's front.
     * - Insert its mapping into the HashMap.
     *
     * CAPACITY ENFORCEMENT
     * --------------------
     * If the number of entries exceeds capacity, evict the LRU entry.
     *
     * The capacity check occurs after either case.
     * Updating an existing key does not increase the map's size,
     * so it will not trigger unnecessary eviction.
     *
     * Time: O(1) average
     *
     * @param key   cache key
     * @param value value to insert or update
     */
    public void put(int key, int value) {

        Node targetNode = map.get(key);

        if (targetNode == null) {

            // CASE 1: Insert a new entry.
            Node newNode = new Node(key, value);

            // Maintain the linked list and HashMap consistently.
            addToFront(newNode);
            map.put(key, newNode);

        } else {

            // CASE 2: Update an existing entry.
            removeNode(targetNode);

            targetNode.value = value;

            // Updating the entry also makes it most recently used.
            addToFront(targetNode);
        }

        // Evict the LRU entry if capacity has been exceeded.
        if (map.size() > capacity) {
            deleteLRUEntry();
        }
    }

    /**
     * Represents one cache entry.
     *
     * The key is stored in the node so that eviction can remove
     * the corresponding HashMap entry.
     */
    private class Node {

        int key;
        int value;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        // Used only for creating the dummy head and tail nodes.
        Node() {
        }
    }

    /**
     * Local tests for revision.
     *
     * LeetCode supplies its own test harness, so this main()
     * is for running the implementation outside LeetCode.
     */
    public static void main(String[] args) {

        // Test 1: Standard LeetCode example.
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        System.out.println(cache.get(1)); // Expected: 1

        cache.put(3, 3);                  // Evicts key 2.

        System.out.println(cache.get(2)); // Expected: -1

        cache.put(4, 4);                  // Evicts key 1.

        System.out.println(cache.get(1)); // Expected: -1
        System.out.println(cache.get(3)); // Expected: 3
        System.out.println(cache.get(4)); // Expected: 4

        // Test 2: Updating an existing key.
        LRUCache updateCache = new LRUCache(2);

        updateCache.put(1, 100);
        updateCache.put(1, 150);

        System.out.println(updateCache.get(1)); // Expected: 150

        // Test 3: Capacity of one.
        LRUCache singleCapacityCache = new LRUCache(1);

        singleCapacityCache.put(1, 100);
        singleCapacityCache.put(2, 200);

        System.out.println(singleCapacityCache.get(1)); // Expected: -1
        System.out.println(singleCapacityCache.get(2)); // Expected: 200
    }
}
