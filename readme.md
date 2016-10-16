This is a Maven project.

Sample input and output:

 > FindValidMoves

    Enter number of pieces: 2

    Piece 1
    Enter colour (W/B): W
    Enter type (B/N/P): P
    Enter position: e4

    Piece 2
    Enter colour (W/B): B
    Enter type (B/N/P): N
    Enter position: c5

    Valid moves
    White P on e4: [e5]
    Black N on c5: [a4, a6, b3, b7, d3, d7, e4, e6]

    Continue (Y/N)?: Y

    Enter number of pieces: 3

    Piece 1
    Enter colour (W/B): B
    Enter type (B/N/P): P
    Enter position: g7

    Piece 2
    Enter colour (W/B): W
    Enter type (B/N/P): P
    Enter position: h6

    Piece 3
    Enter colour (W/B): B
    Enter type (N/P): P
    Enter position: h7

    Valid moves
    Black P on g7: [g5, g6, h6]
    White P on h6: [g7]
    Black P on h7: []

   ---------------------------------
     a   b   c   d   e   f   g   h
 8 |   |   |   |   |   |   |   |   |
   ---------------------------------
 7 |   |   |   |   |   |   | P | P |
   ---------------------------------
 6 |   |   |   |   |   |   |   | p |
   ---------------------------------
 5 |   |   |   |   |   |   |   |   |
   ---------------------------------
 4 |   |   |   |   |   |   |   |   |
   ---------------------------------
 3 |   |   |   |   |   |   |   |   |
   ---------------------------------
 2 |   |   |   |   |   |   |   |   |
   ---------------------------------
 1 |   |   |   |   |   |   |   |   |
   ---------------------------------
     a   b   c   d   e   f   g   h

    Continue (Y/N)?: Y

    Enter number of pieces: 3

    Piece 1
    Enter colour (W/B): W
    Enter type (B/N/P): P
    Enter position: f8

    Pawns may not be placed on the first or last ranks.  Please re-enter.

    Piece 1
    Enter colour (W/B): W
    Enter type (B/N/P): P
    Enter position: f7

    Piece 2
    Enter colour (W/B): B
    Enter type (B/N/P): N
    Enter position: e8

    Piece 3
    Enter colour (W/B): B
    Enter type (B/N/P): P
    Enter position: g7

    Valid moves
    White P on f7: [e8, f8]
    Black N on e8: [c7, d6, f6]
    Black P on g7: [g5, g6]
   ---------------------------------
     a   b   c   d   e   f   g   h
 8 |   |   |   |   | N |   |   |   |
   ---------------------------------
 7 |   |   |   |   |   | P | P |   |
   ---------------------------------
 6 |   |   |   |   |   |   |   |   |
   ---------------------------------
 5 |   |   |   |   |   |   |   |   |
   ---------------------------------
 4 |   |   |   |   |   |   |   |   |
   ---------------------------------
 3 |   |   |   |   |   |   |   |   |
   ---------------------------------
 2 |   |   |   |   |   |   |   |   |
   ---------------------------------
 1 |   |   |   |   |   |   |   |   |
   ---------------------------------
     a   b   c   d   e   f   g   h
    Continue (Y/N)?: Y

    Enter number of pieces: 3

    Piece 1
    Enter colour (W/B): W
    Enter type (B/N/P): B
    Enter position: c2

    Piece 2
    Enter colour (W/B): B
    Enter type (B/N/P): P
    Enter position: d3

    Piece 3
    Enter colour (W/B): B
    Enter type (B/N/P): N
    Enter position: b4

    Valid moves
    White B on c2: [a4, b1, b3, d1, d3]
    Black P on d3: [c2, d2]
    Black N on b4: [a2, a6, c2, c6, d5]
   ---------------------------------
     a   b   c   d   e   f   g   h
 8 |   |   |   |   |   |   |   |   |
   ---------------------------------
 7 |   |   |   |   |   |   |   |   |
   ---------------------------------
 6 |   |   |   |   |   |   |   |   |
   ---------------------------------
 5 |   |   |   |   |   |   |   |   |
   ---------------------------------
 4 |   | N |   |   |   |   |   |   |
   ---------------------------------
 3 |   |   |   | P |   |   |   |   |
   ---------------------------------
 2 |   |   | b |   |   |   |   |   |
   ---------------------------------
 1 |   |   |   |   |   |   |   |   |
   ---------------------------------
     a   b   c   d   e   f   g   h
    Continue (Y/N)?: N








High-level design:
(1) Input numberOfPiece
(2) for(numberOfPiece)
 (2.1) Input(color,type,position)
 (2.2) check validity if wrong then Output and re-input at (2.1)
      else work out allowed position and stored result
(3) continue to (1) if required


Some trick points:
1. need check out of board for all type of pieces,
we may use brute force plus boundary check
2. there is a global state for each group of input,
i.e., sequence does matter in terms of initial position,
however, once all pieces are in place, then input sequence
dose not matter as long as the resulting initial locations
are the same.
3. possible position = normal movement (same color will block movement)
 and capture movement (only different color can be captured), not only depends on
, and constrained to chessboard boundary and type constraint

Class design:
1. chessboard class
   boundary, location mapping (a2) <--> (1,2)
   LinkedHashMap<position, piece> //state of initial positions
2. chess piece (color, position, rules)
   -place(Chessboard), -move(Chessboard)

3. chessManager
   place chess
   work out possible positions
   reset chessboard

4. console (input ->  call chessManager
->output


