module Tests
open System
open Xunit
open Program

[<Fact>]
let ``My test`` () =
    Assert.True(true)

[<Fact>]    
let ``just trying things :) out`` () =
    Assert.True(calculate 1 1 Operators.Add = 2)
    Assert.False(calculate 1 1 Operators.Add = 3)
    Assert.True(calculate 1 2 Operators.Multiply = 2)
    Assert.False(calculate 2 2 Operators.Multiply = 8)
    Assert.Throws<DivideByZeroException>(fun () -> calculate 2 0 Operators.Divide = 0 |> ignore)
    
    
    
    
    
    
    
    
   
