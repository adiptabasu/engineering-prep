# Hashing interview extensions

The core hashing notes cover membership, complements, frequencies, and grouping keys. Two common interview problems apply those same ideas in slightly different ways: encoding a list of strings without ambiguity, and checking uniqueness across several dimensions of a grid.

## Encode and decode strings

In this example, `[start, end)` denotes a half-open string slice: it includes `start` and stops before `end`.

Joining strings with a delimiter is unsafe unless the delimiter is escaped: a string may itself contain that delimiter. A length-prefixed format avoids that ambiguity. Write each string as its character count, a separator, then exactly that many characters:

    encode(strings):
        output = empty string
        for each string s:
            append length(s), "#", and s to output
        return output

    decode(encoded):
        result = empty list
        i = 0
        while i < length(encoded):
            separator = position of "#" at or after i
            size = integer represented by encoded[i .. separator)
            start = separator + 1
            append encoded[start .. start + size) to result
            i = start + size
        return result

The decoder uses the declared length, so the string contents may contain `#`, digits, or be empty. The separator is only used to find the length field. The format assumes the length field uses decimal digits and the chosen separator cannot appear in that numeric field. Both passes take O(total encoded characters) time; the output storage is proportional to the decoded content. Define whether lengths count characters, code units, or bytes consistently between encoder and decoder.

## Valid Sudoku

To check whether a partially filled 9 × 9 Sudoku board violates the rules, track which digits have appeared in each row, column, and 3 × 3 box. Ignore empty cells. A digit is invalid if it is already present in any of the cell's three groups.

    rows = 9 empty sets
    columns = 9 empty sets
    boxes = 9 empty sets

    for r from 0 through 8:
        for c from 0 through 8:
            value = board[r][c]
            if value is empty:
                continue
            box = (r // 3) * 3 + (c // 3)
            if value in rows[r] or value in columns[c] or value in boxes[box]:
                return false
            add value to rows[r], columns[c], and boxes[box]
    return true

Each non-empty cell is checked once, so time is O(81), conventionally O(n²) for an n × n board. The sets use O(n²) space in the general form. This checks whether the existing clues conflict; it does not prove that the board has a solution or that a solution is unique.

## Coverage note

These are useful hashing applications not represented by solution files in the current Arrays & Hashing problem list. They extend the existing key-encoding and frequency-set ideas without changing the scope of the practiced set.
