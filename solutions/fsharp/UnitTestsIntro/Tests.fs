module Tests
open System
open Xunit

let calculate a b op =
    match op with
    | "add" -> a + b
    | "multiply" -> a * b
    | _ -> 0

[<Fact>]
let ``My test`` () =
    Assert.True(true)

[<Fact>]    
let ``just trying shit out`` () =
    Assert.True(calculate 1 1 "add" = 2)
    Assert.False(calculate 1 1 "add" = 3)
    Assert.True(calculate 1 2 "multiply" = 2)
    Assert.False(calculate 2 2 "multiply" = 8)
    
    
    
    
   
