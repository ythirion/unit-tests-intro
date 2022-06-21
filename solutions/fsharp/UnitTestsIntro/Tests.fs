module Tests
open System
open Xunit


let add a b = a + b

type Operators =
    | Add
    | Multiply
    | Divide
    | Subtract

let calculate a b op =
    match op with
    | Operators.Add -> a + b
    | Operators.Multiply -> a * b
    | Operators.Divide -> a / b
    | Operators.Subtract -> a - b
    | _ -> 0

[<Fact>]
let ``My test`` () =
    Assert.True(true)

[<Fact>]    
let ``just trying shit out`` () =
    Assert.True(calculate 1 1 Operators.Add = 2)
    Assert.False(calculate 1 1 Operators.Add = 3)
    Assert.True(calculate 1 2 Operators.Multiply = 2)
    Assert.False(calculate 2 2 Operators.Multiply = 8)
    
    
    
    
   
