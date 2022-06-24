module Program
type Operators =
    | Add
    | Multiply
    | Divide
    | Subtract

let calculate (a: int) (b: int) (op: Operators) =
    match op with
    | Operators.Add      -> a + b
    | Operators.Multiply -> a * b
    | Operators.Divide   -> a / b
    | Operators.Subtract -> a - b