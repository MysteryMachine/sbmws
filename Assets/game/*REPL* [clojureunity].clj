> (.gameObject (symbol "o"))
System.MissingMethodException: Cannot find instance field/property/member name gameObject
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$eval__20218__20223.__interop_gameObject20225 (object) <IL 0x0000e, 0x0004a>
at user$eval__20218__20223.invoke () <IL 0x00019, 0x000fc>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (.gameObject (eval (symbol "o")))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property gameObject can't be resolved (target class is unknown).
#<GameObject Player (UnityEngine.GameObject)>

user=> (.gameObject (eval `(symbol "o")))
System.MissingMethodException: Cannot find instance field/property/member name gameObject
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$eval__20248__20253.__interop_gameObject20255 (object) <IL 0x0000e, 0x0004a>
at user$eval__20248__20253.invoke () <IL 0x0006e, 0x0047d>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (.gameObject `(eval (symbol "o")))
System.MissingMethodException: Cannot find instance field/property/member name gameObject
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$eval__20270__20275.__interop_gameObject20277 (object) <IL 0x0000e, 0x0004a>
at user$eval__20270__20275.invoke () <IL 0x000af, 0x007a6>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (defmacro component-init [obj type & bindings]
  `(let [strmap# (clojure.walk/stringify-keys (apply hash-map '~bindings))
         names# (keys strmap#)
         obj# ~obj
         type# ~type]
    (do
       (add-component (.gameObject obj#) type#))
       (doseq [k# names#]
         (.. obj# gameObject (GetComponent type#) ((eval (symbol k#)))))))
#'user/component-init

user=> (component-init o game.collidable.Collidable :type 1 :a 2 :c 3)
clojure.lang.CljCompiler.Ast.ParseException: Malformed member exception
  at clojure.lang.CljCompiler.Ast.HostExpr+Parser.Parse (clojure.lang.CljCompiler.Ast.ParserContext pcon, System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSeq (clojure.lang.CljCompiler.Ast.ParserContext pcon, ISeq form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro component-init [obj type & bindings]
  `(let [strmap# (clojure.walk/stringify-keys (apply hash-map '~bindings))
         names# (keys strmap#)
         obj# ~obj
         type# ~type]
    (do
       (add-component (.gameObject obj#) type#))
       (doseq [k# names#]
         (.. obj# gameObject (GetComponent type#) ((eval `(symbol k#)))))))
#'user/component-init

user=> (component-init o game.collidable.Collidable :type 1 :a 2 :c 3)
clojure.lang.CljCompiler.Ast.ParseException: Malformed member exception
  at clojure.lang.CljCompiler.Ast.HostExpr+Parser.Parse (clojure.lang.CljCompiler.Ast.ParserContext pcon, System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSeq (clojure.lang.CljCompiler.Ast.ParserContext pcon, ISeq form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (.gameObject (eval `(symbol "o")))
System.MissingMethodException: Cannot find instance field/property/member name gameObject
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$eval__20567__20572.__interop_gameObject20574 (object) <IL 0x0000e, 0x0004a>
at user$eval__20567__20572.invoke () <IL 0x0006e, 0x0047d>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (.gameObject (eval (symbol "o")))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property gameObject can't be resolved (target class is unknown).
#<GameObject Player (UnityEngine.GameObject)>

user=> ((eval (symbol ".gameObject")) o)
System.InvalidOperationException: Unable to resolve symbol: .gameObject in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> ((eval `(symbol ".gameObject")) o)
nil

user=> (`(eval (symbol ".gameObject")) o)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__20639__20644.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> ((eval (symbol ".gameObject")) o)
System.InvalidOperationException: Unable to resolve symbol: .gameObject in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> ((eval `(symbol ".gameObject")) o)
nil

user=> .gameObject
System.InvalidOperationException: Unable to resolve symbol: .gameObject in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro component-init [obj type & bindings]
  `(let [strmap# (clojure.walk/stringify-keys (apply hash-map '~bindings))
         names# (keys strmap#)
         obj# ~obj
         type# ~type]
    (do
       (add-component (.gameObject obj#) type#))
       (doseq [k# names#]
         (.. obj# gameObject (GetComponent type#) (#((eval (symbol k#)) %))))))
#'user/component-init

user=> .gameObject
System.InvalidOperationException: Unable to resolve symbol: .gameObject in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (component-init o game.collidable.Collidable :type 1 :a 2 :c 3)

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> 

user=> { (symbol test) a }
System.InvalidOperationException: Unable to resolve symbol: a in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> { '(symbol test) a }
System.InvalidOperationException: Unable to resolve symbol: a in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> { '(symbol test) 1 }
{(symbol test) 1}

user=> { 'a 1 }
{a 1}

user=> { a 1 }
System.InvalidOperationException: Unable to resolve symbol: a in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> { 'a 1 }
{a 1}

user=> (defmacro component-init [obj type & bindings]
  `(let [names# (keys '~bindings)
         obj# ~obj
         type# ~type] names#))
#'user/component-init

user=> (component-init o game.collidable.Collidable :type 1 :a 2 :c 3)


user=> (defmacro component-init [obj type & bindings]
  `(let [strmap# (apply hash-map '~bindings))|
         names# (keys strmap#)
         obj# ~obj
         type# ~type] names#)

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro component-init [obj type & bindings]
  `(let [strmap# (apply hash-map '~bindings)
         names# (keys strmap#)
         obj# ~obj
         type# ~type] names#))
#'user/component-init

user=> (component-init o game.collidable.Collidable :type 1 :a 2 :c 3)
(:type :c :a)

user=> (stringify 1)
System.InvalidOperationException: Unable to resolve symbol: stringify in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (stringify "1")
System.InvalidOperationException: Unable to resolve symbol: stringify in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (str :1)
System.ArgumentException: Invalid token: :1
  at clojure.lang.LispReader.InterpretToken (System.String rawToken, System.String token, System.String mask) [0x00000] in <filename unknown>:0 
  at clojure.lang.LispReader.read (clojure.lang.PushbackTextReader r, Boolean eofIsError, System.Object eofValue, Boolean isRecursive) [0x00000] in <filename unknown>:0 
user=> (str :arcadia)
":arcadia"

user=> (defmacro component-init [obj type & bindings]
  `(let [strmap# (apply hash-map '~bindings)
         names# (clojure.walk/stringify-keys (keys strmap#))
         obj# ~obj
         type# ~type]
    (do
       (add-component (.gameObject obj#) type#))
       (doseq [k# names#]
         (.. obj# gameObject (GetComponent type#) (~(eval (symbol k#)))))))
System.InvalidOperationException: Unable to resolve symbol: k# in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro component-init [obj type & bindings]
  `(let [strmap# (apply hash-map '~bindings)
         names# (clojure.walk/stringify-keys (keys strmap#))
         obj# ~obj
         type# ~type]
    (do
       (add-component (.gameObject obj#) type#))
       (doseq [k# names#]
         (.. obj# gameObject (GetComponent type#) ((eval (symbol k#)))))))
#'user/component-init

user=> (component-init o game.collidable.Collidable :type 1 :a 2 :c 3)
clojure.lang.CljCompiler.Ast.ParseException: Malformed member exception
  at clojure.lang.CljCompiler.Ast.HostExpr+Parser.Parse (clojure.lang.CljCompiler.Ast.ParserContext pcon, System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSeq (clojure.lang.CljCompiler.Ast.ParserContext pcon, ISeq form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro component-init [obj type & bindings]
  `(let [strmap# (apply hash-map '~bindings)
         names# (clojure.walk/stringify-keys (keys strmap#))
         obj# ~obj
         type# ~type]
    (do
       (add-component (.gameObject obj#) type#))
       (doseq [k# names#]
         (eval (symbol k#)))))
#'user/component-init

user=> (component-init o game.collidable.Collidable :type 1 :a 2 :c 3)
System.InvalidCastException: Cannot cast from source type to destination type.
  at clojure/core$symbol__386.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__21137__21146.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> 

user=> 

user=> (defmacro mac [obj n] `(.. obj #((eval (symbol k#)) %)))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: No such var: user/obj
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro mac [obj n] `(.. obj (symbol k#))))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro mac [obj n] `(.. obj ((symbol n)) ))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: No such var: user/obj
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro mac [obj n] `(.. ~obj ((symbol n)) ))
#'user/mac

user=> (defmacro mac [obj n] `(.. ~obj ((symbol ~n)) ))
#'user/mac

user=> (mac o "gameObject")
clojure.lang.CljCompiler.Ast.ParseException: Malformed member exception
  at clojure.lang.CljCompiler.Ast.HostExpr+Parser.Parse (clojure.lang.CljCompiler.Ast.ParserContext pcon, System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSeq (clojure.lang.CljCompiler.Ast.ParserContext pcon, ISeq form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro mac [obj n] `(.. ~obj #( ((symbol ~n)) %) ))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: Unable to resolve symbol: p1__21341__21342__auto__ in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro mac [obj n] `(.. ~obj #( `((symbol ~n)) %) ))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: Unable to resolve symbol: p1__21381__21382__auto__ in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro mac [obj n] `(.. ~obj #( (eval (symbol ~n)) %) ))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: Unable to resolve symbol: p1__21421__21422__auto__ in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro mac [obj n] `(.. ~obj #((eval (symbol ~n)) %) ))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: Unable to resolve symbol: p1__21461__21462__auto__ in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro mac [obj n] `(.. ~obj #((eval `(symbol ~n)) %) ))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: Unable to resolve symbol: p1__21501__21502__auto__ in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro mac [obj n] `(.. ~obj #((symbol ~n) %) ))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: Unable to resolve symbol: p1__21541__21542__auto__ in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (send o "gameObject")
System.InvalidCastException: Cannot cast from source type to destination type.
  at clojure/core$send__1909.doInvoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.RestFn.invoke (System.Object arg1, System.Object arg2) [0x00000] in <filename unknown>:0 
  at user$eval__21588__21593.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (send o gameObject)
System.InvalidOperationException: Unable to resolve symbol: o in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (send (object-named "Player") gameObject)
System.InvalidOperationException: Unable to resolve symbol: gameObject in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (send (object-named "Player") "gameObject")
System.InvalidCastException: Cannot cast from source type to destination type.
  at clojure/core$send__1909.doInvoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.RestFn.invoke (System.Object arg1, System.Object arg2) [0x00000] in <filename unknown>:0 
  at user$eval__18302__18307.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro mac [obj n] `(.. ~obj (apply (symbol ~n)) ))
#'user/mac

user=> (mac o "gameObject")
System.InvalidOperationException: Unable to resolve symbol: o in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (mac (object-named "Player") "gameObject")
System.MissingMethodException: Cannot find member apply matching args
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,object) <IL 0x0002d, 0x0008e>
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,object) <IL 0x00137, 0x005ea>
at user$eval__18354__18359.__interop_apply18361 (object,object) <IL 0x0000f, 0x00051>
at user$eval__18354__18359.invoke () <IL 0x00032, 0x001de>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (defmacro mac [obj n] `(.. ~obj #(apply (symbol ~n) %) ))
#'user/mac

user=> (mac (object-named "Player") "gameObject")
System.InvalidOperationException: Unable to resolve symbol: p1__18363__18364__auto__ in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> Malforme\\
#############
## RESTART ##
#############
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/repl.clj:150:40 - reference to field/property Read can't be resolved (target class is unknown).
; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> (Math/sqrt 10)
System.MissingMethodException: Cannot find member sqrt matching args
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,long) <IL 0x00030, 0x000c7>
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,long) <IL 0x0013c, 0x00647>
at user$eval__18542__18547.__interop_sqrt18549 (object,long) <IL 0x0000f, 0x0005e>
at user$eval__18542__18547.invoke () <IL 0x00013, 0x0002e>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (Math.sqrt 10)
Unable to find type: Math.sqrt, compiling: (NO_SOURCE_PATH:0:0)
user=> (Math/root 10)
System.MissingMethodException: Cannot find member root matching args
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,long) <IL 0x00030, 0x000c7>
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,long) <IL 0x0013c, 0x00647>
at user$eval__18569__18574.__interop_root18576 (object,long) <IL 0x0000f, 0x0005e>
at user$eval__18569__18574.invoke () <IL 0x00013, 0x0002e>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (sqrt 10)
System.InvalidOperationException: Unable to resolve symbol: sqrt in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (Math/sqrt 10.0)
System.MissingMethodException: Cannot find member sqrt matching args
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,double) <IL 0x00030, 0x000c7>
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,double) <IL 0x0013c, 0x0061c>
at user$eval__18819__18824.__interop_sqrt18826 (object,double) <IL 0x0000f, 0x00053>
at user$eval__18819__18824.invoke () <IL 0x00013, 0x00031>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (import [Math])
nil

user=> 
***Repl Killed***
(Math/sqrt 10.0)

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (import [Math])
nil

user=> (Math/sqrt 10.0)
System.MissingMethodException: Cannot find member sqrt matching args
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,double) <IL 0x00030, 0x000c7>
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object,double) <IL 0x0013c, 0x0061c>
at user$eval__18895__18900.__interop_sqrt18902 (object,double) <IL 0x0000f, 0x00053>
at user$eval__18895__18900.invoke () <IL 0x00013, 0x00031>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (doc Math/sqrt)
nil

user=> (Math/Sqrt 10.0)
3.16227766016838

user=> (Math/Sqrt 0)
0.0

user=> 
***Repl Killed***

#############
## RESTARReflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/repl.clj:150:40 - reference to field/property Read can't be resolved (target class is unknown).
; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [v v
           { x :x y :y z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 ~dx ~dy ~dz))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (vector3 1 2 3)
(1.0, 2.0, 3.0)

user=> (vector3 (do (print "test" )1) 2 3)
test(1.0, 2.0, 3.0)

user=> (def a [a] (vector3 a a a)))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> 

user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [v v
           { x :x y :y z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 ~dx ~dy ~dz))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (defn b [a] (vector3 a a a)))

***Repl Killed***

#############
## RESTARReflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/repl.clj:150:40 - reference to field/property Read can't be resolved (target class is unknown).
; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [v v
           { x :x y :y z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 ~dx ~dy ~dz))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (defn b [a] (vector3 a a a)))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [v v
           { x :x y :y z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 ~dx ~dy ~dz))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (vector3 (do (print "test" )1) 2 3)
test(1.0, 2.0, 3.0)

user=> (defn- operate [op -a -b]
  (let [c (max (-count -a)(-count -b))
        a (if (number? -a) (vec (take c (repeat -a)))
              (-vec -a))
        b (if (number? -b) (vec (take c (repeat -b)))
              (-vec -b))]
    (map #(op (get-or a % 0) (get-or b % 0)) (range c))))

(defn- reduce-operate [op col]
  (vec (reduce #(operate op %1 %2) col)))

(defn v+ [& more] (reduce-operate + more))
System.InvalidOperationException: Unable to resolve symbol: -count in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> #'user/reduce-operate

user=> 
user=> #'user/v+

user=> (defn- -count [o]
  (cond (number? o) 1
        (sequential? o) (count o)
        (vector3? o) 3
        (vector2? o) 2
        (vector4? o) 4
        (color? o) 4)) 
System.InvalidOperationException: Unable to resolve symbol: vector3? in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=>  
(declare position!)

(defn log [x] 
  (UnityEngine.Debug/Log x))

(defn vector2? [x] (instance? UnityEngine.Vector2 x))
(defn vector3? [x] (instance? UnityEngine.Vector3 x))
(defn vector4? [x] (instance? UnityEngine.Vector4 x))
(defn transform? [x] (instance? UnityEngine.Transform x))
(defn quaternion? [x] (instance? UnityEngine.Quaternion x))
(defn color? [x] (instance? UnityEngine.Color x))
(defn gameobject? [x] (instance? UnityEngine.GameObject x))

(defn- get-or [col idx nf] (or (get col idx) nf))
    
(defn- -count [o]
  (cond (number? o) 1
        (sequential? o) (count o)
        (vector3? o) 3
        (vector2? o) 2
        (vector4? o) 4
        (color? o) 4)) 

user=> #'user/position!

user=> 
user=> #'user/log

user=> 
user=> #'user/vector2?

user=> #'user/vector3?

user=> #'user/vector4?

user=> #'user/transform?

user=> #'user/quaternion?

user=> #'user/color?

user=> #'user/gameobject?

user=> 
user=> #'user/get-or

user=> 
user=> #'user/-count

user=> (defn v+ [& more] (reduce-operate + more))
#'user/v+

user=> (v+ (vector3 -1 -1 -1) (vector3 1 1 1))
System.InvalidOperationException: Unable to resolve symbol: vector3 in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [v v
           { x :x y :y z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 ~dx ~dy ~dz))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (v+ (vector3 -1 -1 -1) (vector3 1 1 1))
clojure.lang.ArityException: Wrong number of args (3) passed to: Var+Unbound
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3) [0x00000] in <filename unknown>:0 
  at user$reduce_operate$fn__18297__18301.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9762$fn__9767__9771.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$reduce_operate__18304.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$v_PLUS___18477.doInvoke (System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.RestFn.invoke (System.Object arg1, System.Object arg2) [0x00000] in <filename unknown>:0 
  at user$eval__18529__18534.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> '((vector3 -1 -1 -1) (vector3 1 1 1))
((vector3 -1 -1 -1) (vector3 1 1 1))

user=> (Vector3/op_Addition (vector3 -1 -1 -1) (vector3 1 1 1))
(0.0, 0.0, 0.0)

user=> (comp identity +)
#<clojure/core$comp$fn__2200__2208 clojure/core$comp$fn__2200__2208>

user=> (comp identity Vector3/op_Addition)
System.InvalidOperationException: Unable to find static field: op_Addition in UnityEngine.Vector3
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro v3+ [v & more]
  `(reduce #(Vector3/op_Addition %1 %2) v more))
#'user/v3+
\
user=> (v3)
System.InvalidOperationException: Unable to resolve symbol: v3 in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (v3+ (vector3 -1 -1 -1) (vector3 1 1 1) (vector3 2 2 2))
System.InvalidOperationException: No such var: user/v
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro v3+ [v & more]
  `(reduce #(Vector3/op_Addition %1 %2) ~v ~more))
#'user/v3+

user=> (v3+ (vector3 -1 -1 -1) (vector3 1 1 1) (vector3 2 2 2))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__18687__18699.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (v3+ (vector3 -1 -1 -1) (vector3 1 1 1) (vector3 2 2 2))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__18708__18720.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro v3+ [v & more]
  `(reduce #(Vector3/op_Addition %1 %2) ~v ~more))
#'user/v3+

user=> (defmacro v3+ [v & more]
  `'(reduce #(Vector3/op_Addition %1 %2) ~v ~more))
#'user/v3+

user=> (v3+ (vector3 -1 -1 -1) (vector3 1 1 1) (vector3 2 2 2))
(clojure.core/reduce (fn* [p1__18753__18755__auto__ p2__18754__18756__auto__] (Vector3/op_Addition p1__18753__18755__auto__ p2__18754__18756__auto__)) (vector3 -1 -1 -1) ((vector3 1 1 1) (vector3 2 2 2)))

user=> (eval (v3+ (vector3 -1 -1 -1) (vector3 1 1 1) (vector3 2 2 2)))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__18812__18824.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__18805__18810.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce - 5 [3 2])
0

user=> (reduce #(Vector3/op_Addition %1 %2) (vector3 -1 -1 -1) '((vector3 1 1 1) (vector3 2 2 2)))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__18849$fn__18854__18858.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__18849__18861.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (#(Vector3/op_Addition %1 %2) (vector3 -1 -1 -1) (vector3 1 1 1))
(0.0, 0.0, 0.0)

user=> (reduce #(Vector3/op_Addition %1 %2) '((vector3 -1 -1 -1) (vector3 1 1 1) (vector3 2 2 2)))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__18895$fn__18900__18904.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__18895__18907.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce #(Vector3/op_Addition %1 %2) '((vector3 -1 -1 -1) (vector3 1 1 1) (vector3 2 2 2)))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__18918$fn__18923__18927.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__18918__18930.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce #(Vector3/op_Addition %1 %2) '((vector3 -1 -1 -1) (vector3 1 1 1) (vector3 2 2 2)))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__18941$fn__18946__18950.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__18941__18953.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (def a (vector3 1 1 ))
System.MissingMethodException: Cannot find instance field/property/member name x
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$vector3__18503.__interop_x18505 (object) <IL 0x0000e, 0x0004a>
at user$vector3__18503.invoke (object,object,object,object) <IL 0x000dc, 0x00761>
at clojure.lang.Var.invoke (object,object,object,object) <IL 0x0002f, 0x000e5>
at clojure.lang.AFn.ApplyToHelper (clojure.lang.IFn,clojure.lang.ISeq) <IL 0x00118, 0x004da>
at clojure.lang.Var.applyTo (clojure.lang.ISeq) <IL 0x00002, 0x00027>
at clojure.lang.Compiler.MacroexpandSeq1 (clojure.lang.ISeq) <IL 0x0003d, 0x001b0>
, compiling: (NO_SOURCE_PATH:0:0)
user=> (def a (vector3 1 1 1))
#'user/a

user=> (reduce #(Vector3/op_Addition %1 %2) '(a a a))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__18978$fn__18983__18987.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__18978__18990.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce #(Vector3/op_Addition %1 %2) [a a a])
(3.0, 3.0, 3.0)

user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) (vec ~more)))
#'user/v3+

user=> (v+ [a a a])
[(1.0, 1.0, 1.0) (1.0, 1.0, 1.0) (1.0, 1.0, 1.0)]

user=> (v3+ [a a a])
clojure.lang.ArityException: Wrong number of args (0) passed to: PersistentVector
  at clojure.lang.AFn.invoke () [0x00000] in <filename unknown>:0 
  at user$eval__19067__19079.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (v3+ [a a a])
clojure.lang.ArityException: Wrong number of args (0) passed to: PersistentVector
  at clojure.lang.AFn.invoke () [0x00000] in <filename unknown>:0 
  at user$eval__19088__19100.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) (apply vec ~more)))
#'user/v3+

user=> (v3+ [a a a])
clojure.lang.ArityException: Wrong number of args (0) passed to: PersistentVector
  at clojure.lang.AFn.invoke () [0x00000] in <filename unknown>:0 
  at user$eval__19140__19152.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (vec '(a a a))
[a a a]

user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) (vec ~more)))
#'user/v3+

user=> (vec '(a a a))
[a a a]

user=> (v3+ a a a)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19220__19232.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (v+ a a a)
clojure.lang.ArityException: Wrong number of args (3) passed to: Var+Unbound
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3) [0x00000] in <filename unknown>:0 
  at user$reduce_operate$fn__18297__18301.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9762$fn__9767__9771.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$reduce_operate__18304.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$v_PLUS___18477.doInvoke (System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.RestFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3) [0x00000] in <filename unknown>:0 
  at user$eval__19241__19246.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (v3+ a a a)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19255__19267.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce #(Vector3/op_Addition %1 %2) [a a a])
(3.0, 3.0, 3.0)

user=> (reduce #(Vector3/op_Addition %1 %2) [a a a a a])
(5.0, 5.0, 5.0)

user=> (reduce #(Vector3/op_Addition %1 %2) [a a a a a])
(5.0, 5.0, 5.0)

user=> (reduce #(Vector3/op_Addition %1 %2) '(a a a a a))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19347$fn__19352__19356.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19347__19359.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce #(Vector3/op_Addition %1 %2) a a a a a)
clojure.lang.ArityException: Wrong number of args (6) passed to: clojure/core/reduce--10414
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4, System.Object arg5, System.Object arg6) [0x00000] in <filename unknown>:0 
  at user$eval__19370__19382.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce #(Vector3/op_Addition %1 %2) [a a a a a])
(5.0, 5.0, 5.0)

user=> (reduce #(Vector3/op_Addition %1 %2) (list a a a a a))
(5.0, 5.0, 5.0)

user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) (apply list ~more)))
#'user/v3+

user=> (v3+ a a a)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19468__19480.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce #(Vector3/op_Addition %1 %2) (apply list'(a a a a))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (reduce #(Vector3/op_Addition %1 %2) (apply list '(a a a a))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (reduce #(Vector3/op_Addition %1 %2) (apply list '(a a a a)))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19583$fn__19588__19592.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19583__19595.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (reduce #(Vector3/op_Addition %1 %2) [a a a a])
(4.0, 4.0, 4.0)

user=> (defmacro sequize [& more] `~more)
#'user/sequize

user=> (sequize 1 2 3)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19654__19659.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro sequize [& more] `more)
#'user/sequize

user=> (sequize 1 2 3)
System.InvalidOperationException: No such var: user/more
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro sequize [& more] (~more))
#'user/sequize

user=> (sequize 1 2 3)
clojure.lang.ArityException: Wrong number of args (-1) passed to: Var+Unbound
  at clojure.lang.Compiler.MacroexpandSeq1 (ISeq form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.macroexpand1 (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Macroexpand (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro sequize [& more] ~more)
#'user/sequize

user=> (sequize 1 2 3)
clojure.lang.ArityException: Wrong number of args (-1) passed to: Var+Unbound
  at clojure.lang.Compiler.MacroexpandSeq1 (ISeq form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.macroexpand1 (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Macroexpand (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro sequize [& more] ~'more)
#'user/sequize

user=> (sequize 1 2 3)
clojure.lang.ArityException: Wrong number of args (-1) passed to: Var+Unbound
  at clojure.lang.Compiler.MacroexpandSeq1 (ISeq form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.macroexpand1 (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Macroexpand (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro sequize [& more] `'~more)
#'user/sequize

user=> (sequize 1 2 3)
(1 2 3)

user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) '~more))
#'user/v3+

user=> (v3+ a a a)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19876$fn__19881__19885.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19876__19888.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (v3+ a a a)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19897$fn__19902__19906.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19897__19909.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> a
(1.0, 1.0, 1.0)

user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) `'~more))
#'user/v3+

user=> (v3+ a a a)
System.InvalidOperationException: No such var: user/more
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) '(~more)))
#'user/v3+

user=> (v3+ a a a)
(a a a)

user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) '[~more]))
#'user/v3+

user=> (v3+ a a a)
(a a a)

user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) '~more))
#'user/v3+

user=> (v3+ a a a)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__20109$fn__20114__20118.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9748$fn__9753__9757.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9589$fn__9594$G__9558__9611__9614.invoke (System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$seq_reduce__9641.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9688$fn__9693__9698.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core/protocols$eval__9506$fn__9511$G__9475__9533__9536.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure/core$reduce__10414.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__20109__20121.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro v3+ [& more]
  `(reduce #(Vector3/op_Addition %1 %2) (vec ~more))
)
#'user/v3+

user=> (v3+ a a a)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__20161__20173.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vplus [& more]
  `(reduce #(Vector3/op_Addition %1 %2) '~more))
#'user/vplus

user=> (v3+ a a a)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__20213__20225.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defn v3+ [& more]
  (reduce #(Vector3/op_Addition %1 %2) more))
#'user/v3+

user=> (v3+ a a a)
(3.0, 3.0, 3.0)

user=> (vector3 (vector3 1 1 1) {:x 4 :y 2 :z 3})
System.InvalidOperationException: Unable to resolve symbol: vector3 in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [v v
           { x :x y :y z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 (float ~dx) (float ~dy) (float ~dz)))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (vector3 (vector3 1 1 1) {:x 4 :y 2 :z 3})
(4.0, 2.0, 3.0)

user=> (vector3 (vector3 1 1 1) {:x 4 :y 2 })
System.MissingMethodException: Cannot find instance field/property/member name z
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$vector3__18297.__interop_z18301 (object) <IL 0x0000e, 0x0004a>
at user$vector3__18297.invoke (object,object,object,object) <IL 0x00152, 0x00a00>
at clojure.lang.Var.invoke (object,object,object,object) <IL 0x0002f, 0x000e5>
at clojure.lang.AFn.ApplyToHelper (clojure.lang.IFn,clojure.lang.ISeq) <IL 0x00118, 0x004da>
at clojure.lang.Var.applyTo (clojure.lang.ISeq) <IL 0x00002, 0x00027>
at clojure.lang.Compiler.MacroexpandSeq1 (clojure.lang.ISeq) <IL 0x0003d, 0x001b0>
, compiling: (NO_SOURCE_PATH:0:0)
user=> (vector3 (vector3 1 1 1) {:x 4 :y 2 })
System.MissingMethodException: Cannot find instance field/property/member name z
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at user$vector3__18297.__interop_z18301 (object) <IL 0x0000e, 0x0004a>
at user$vector3__18297.invoke (object,object,object,object) <IL 0x00152, 0x00a00>
at clojure.lang.Var.invoke (object,object,object,object) <IL 0x0002f, 0x000e5>
at clojure.lang.AFn.ApplyToHelper (clojure.lang.IFn,clojure.lang.ISeq) <IL 0x00118, 0x004da>
at clojure.lang.Var.applyTo (clojure.lang.ISeq) <IL 0x00002, 0x00027>
at clojure.lang.Compiler.MacroexpandSeq1 (clojure.lang.ISeq) <IL 0x0003d, 0x001b0>
, compiling: (NO_SOURCE_PATH:0:0)
user=> (.z (vector3 1 1 1))
1.0

user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [{ x :x y :y z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 (float ~dx) (float ~dy) (float ~dz)))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (.z (vector3 1 1 1))
1.0

user=> (vector3 (vector3 1 1 1) {:x 4 :y 2 })
System.MissingMethodException: Cannot find instance field/property/member name z
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$vector3__18371.__interop_z18375 (object) <IL 0x0000e, 0x0004a>
at user$vector3__18371.invoke (object,object,object,object) <IL 0x0014e, 0x009d8>
at clojure.lang.Var.invoke (object,object,object,object) <IL 0x0002f, 0x000e5>
at clojure.lang.AFn.ApplyToHelper (clojure.lang.IFn,clojure.lang.ISeq) <IL 0x00118, 0x004da>
at clojure.lang.Var.applyTo (clojure.lang.ISeq) <IL 0x00002, 0x00027>
at clojure.lang.Compiler.MacroexpandSeq1 (clojure.lang.ISeq) <IL 0x0003d, 0x001b0>
, compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([^Vector3 v changeset] 
  	(let  [{ x :x 
             y :y 
             z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 (float ~dx) (float ~dy) (float ~dz)))))
#'user/vector3

user=> (vector3 (vector3 1 1 1) {:x 4 :y 2 })
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$vector3__18424.invoke (System.Object , System.Object , System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.Var.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.Var.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.MacroexpandSeq1 (ISeq form) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [{ x :x 
             y :y 
             z :z } changeset
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 (float ~dx) (float ~dy) (float ~dz)))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (vector3 (vector3 1 1 1) {:x 4 :y 2 })
System.MissingMethodException: Cannot find instance field/property/member name z
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$vector3__18460.__interop_z18464 (object) <IL 0x0000e, 0x0004a>
at user$vector3__18460.invoke (object,object,object,object) <IL 0x0014e, 0x009d8>
at clojure.lang.Var.invoke (object,object,object,object) <IL 0x0002f, 0x000e5>
at clojure.lang.AFn.ApplyToHelper (clojure.lang.IFn,clojure.lang.ISeq) <IL 0x00118, 0x004da>
at clojure.lang.Var.applyTo (clojure.lang.ISeq) <IL 0x00002, 0x00027>
at clojure.lang.Compiler.MacroexpandSeq1 (clojure.lang.ISeq) <IL 0x0003d, 0x001b0>
, compiling: (NO_SOURCE_PATH:0:0)
user=> (vector3 (vector3 1 1 1))
clojure.lang.ArityException: Wrong number of args (1) passed to: user/vector3--18460
  at clojure.lang.Compiler.MacroexpandSeq1 (ISeq form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.macroexpand1 (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Macroexpand (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (vector3 (vector3 1 1 1) {})
System.MissingMethodException: Cannot find instance field/property/member name x
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$vector3__18460.__interop_x18462 (object) <IL 0x0000e, 0x00045>
at user$vector3__18460.invoke (object,object,object,object) <IL 0x000d8, 0x00772>
at clojure.lang.Var.invoke (object,object,object,object) <IL 0x0002f, 0x000e5>
at clojure.lang.AFn.ApplyToHelper (clojure.lang.IFn,clojure.lang.ISeq) <IL 0x00118, 0x004da>
at clojure.lang.Var.applyTo (clojure.lang.ISeq) <IL 0x00002, 0x00027>
at clojure.lang.Compiler.MacroexpandSeq1 (clojure.lang.ISeq) <IL 0x0003d, 0x001b0>
, compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [x  (:x changeset)
           y  (:y changeset)
           z  (:z changeset)
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 (float ~dx) (float ~dy) (float ~dz)))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (vector3 (vector3 1 1 1) {})
System.MissingMethodException: Cannot find instance field/property/member name x
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$vector3__18512.__interop_x18514 (object) <IL 0x0000e, 0x0004a>
at user$vector3__18512.invoke (object,object,object,object) <IL 0x000eb, 0x0054c>
at clojure.lang.Var.invoke (object,object,object,object) <IL 0x0002f, 0x000e5>
at clojure.lang.AFn.ApplyToHelper (clojure.lang.IFn,clojure.lang.ISeq) <IL 0x00118, 0x004da>
at clojure.lang.Var.applyTo (clojure.lang.ISeq) <IL 0x00002, 0x00027>
at clojure.lang.Compiler.MacroexpandSeq1 (clojure.lang.ISeq) <IL 0x0003d, 0x001b0>
, compiling: (NO_SOURCE_PATH:0:0)
user=> (:x {})
nil

user=> (let [a nil] a)
nil

user=> (.v a)
System.InvalidOperationException: Unable to resolve symbol: a in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (.z a)
System.InvalidOperationException: Unable to resolve symbol: a in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (.z (vector3 1 2 3))
3.0

user=> (.z (vector3 1 2 3))
3.0

user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let  [x  (:x changeset)
           y  (:y changeset)
           z  (:z changeset)
           a (do (print v) "a")
           dx (or x (.x v))
           dy (or y (.y v))
           dz (or z (.z v))]
        `(vector3 (float ~dx) (float ~dy) (float ~dz)))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (.z (vector3 1 2 3))
3.0

user=> (vector3 (vector3 1 1 1) {:x 1 :y 2 :z 3})
(vector3 1 1 1)(1.0, 2.0, 3.0)

user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	`(let [v# ~v
           { x# :x y# :y z# :z } '~changeset
           dx# (or x# (.x v#))
           dy# (or y# (.y v#))
           dz# (or z# (.z v#))]
        (vector3 dx# dy# dz#))))
#'user/vector3

user=> (vector3 (vector3 1 1 1) {:x 1 :y 2 :z 3})
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
(1.0, 2.0, 3.0)

user=> (vector3 (vector3 1 1 1) {:x 1 :y 2 })
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
(1.0, 2.0, 1.0)

user=> 
(defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	`(let [v# ~v
           { x# :x y# :y z# :z } '~changeset
           dx# (or x# (.x v#))
           dy# (or y# (.y v#))
           dz# (or z# (.z v#))]
        (vector3 dx# dy# dz#))))

user=> Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/repl.clj:150:40 - reference to field/property Read can't be resolved (target class is unknown).
#'user/vector3

user=> (vector3 1 2 3)
(1.0, 2.0, 3.0)

user=> (vector3 1 2 3)
(1.0, 2.0, 3.0)

user=> (vector3 (vector3 1 2 3) {:x 1})
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
(1.0, 2.0, 3.0)

user=> (vector3 (vector3 1 2 3) {:x 4})
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
(4.0, 2.0, 3.0)

user=> (defn- fy [t loops]    (cos (* loops t)))
System.InvalidOperationException: Unable to resolve symbol: cos in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro sin [t] `(Math/Sin ~t))
(defmacro cos [t] `(Math/Cos ~t))
#'user/sin

user=> #'user/cos

user=> (defn- fy [t loops]    (cos (* loops t)))
#'user/fy

user=> (fy 1 1)
0.54030230586814

user=> (fy (float 1) (float 1))
0.54030230586814

user=> (defn v3 [a b c] (vector3 a b c))
System.InvalidOperationException: Unable to resolve symbol: vector3 in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	`(let [v# ~v
           { x# :x y# :y z# :z } '~changeset
           dx# (or x# (.x v#))
           dy# (or y# (.y v#))
           dz# (or z# (.z v#))]
        (vector3 dx# dy# dz#))))
#'user/vector3

user=> (defn v3 [a b c] (vector3 a b c))
#'user/v3

user=> (v3 1 2 3)
(1.0, 2.0, 3.0)

user=> (* 10000 2 Math/PI)
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/repl.clj:150:40 - reference to field/property Read can't be resolved (target class is unknown).
62831.8530717959

user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let [{ x :x y :y z :z } changeset
            dx (or x (.x v))
            dy (or y (.y v))
            dz (or z (.z v))]
       (vector3 dx dy dz))))
)
)

***Repl Killed***

#############
## RESTART ##
#############
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/string.clj:62:3 - call to method Replace can't be resolved (target class is unknown).
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/string.clj:104:32 - call to method Replace can't be resolved (target class is unknown).
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/string.clj:112:3 - call to method Replace on System.Text.RegularExpressions.Regex can't be resolved (argument types: System.String, System.Delegate, System.Int64).
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/string.clj:320:20 - reference to field/property Value can't be resolved (target class is unknown).
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/repl.clj:150:40 - reference to field/property Read can't be resolved (target class is unknown).
; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> 
***Repl Killed***


user=> System.InvalidOperationException: Unable to resolve symbol: ***Repl in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let [{ x :x y :y z :z } changeset
            dx (or x (.x v))
            dy (or y (.y v))
            dz (or z (.z v))]
       (vector3 dx dy dz))))
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
#'user/vector3

user=> (vector3 1 2 3)
(1.0, 2.0, 3.0)

user=> (vector3 (vector3 1 2 3) {:x 1})
System.MissingMethodException: Cannot find instance field/property/member name y
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$vector3__18332.__interop_y18335 (object) <IL 0x0000e, 0x0004a>
at user$vector3__18332.invoke (object,object,object,object) <IL 0x00113, 0x007ac>
at clojure.lang.Var.invoke (object,object,object,object) <IL 0x0002f, 0x000e5>
at clojure.lang.AFn.ApplyToHelper (clojure.lang.IFn,clojure.lang.ISeq) <IL 0x00118, 0x004da>
at clojure.lang.Var.applyTo (clojure.lang.ISeq) <IL 0x00002, 0x00027>
at clojure.lang.Compiler.MacroexpandSeq1 (clojure.lang.ISeq) <IL 0x0003d, 0x001b0>
, compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let [{ x :x y :y z :z } changeset
            dx (or x `(.x v#))
            dy (or y `(.y v#))
            dz (or z `(.z v#))]
       `(let [v# ~v]
         (vector3 ~dx ~dy ~dz)))))
#'user/vector3

user=> (vector3 (vector3 1 2 3) {:x 1})
System.InvalidOperationException: Unable to resolve symbol: v__18373__auto__ in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let [{ x :x y :y z :z } changeset
            dx (or x `(.x ~v))
            dy (or y `(.y ~v))
            dz (or z `(.z ~v))]
       `(vector3 ~dx ~dy ~dz))))

***Repl Killed***

#############
## RESTAR
***Repl Killed***
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/string.clj:62:3 - call to method Replace can't be resolved (target class is unknown).
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/string.clj:104:32 - call to method Replace can't be resolved (target class is unknown).
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/string.clj:112:3 - call to method Replace on System.Text.RegularExpressions.Regex can't be resolved (argument types: System.String, System.Delegate, System.Int64).
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/string.clj:320:20 - reference to field/property Value can't be resolved (target class is unknown).
Reflection warning, /Users/salbecker/Documents/Git/sbmws/Assets/Arcadia/Source/clojure/repl.clj:150:40 - reference to field/property Read can't be resolved (target class is unknown).
; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############
System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> 
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let [{ x :x y :y z :z } changeset
            dx (or x `(.x ~v))
            dy (or y `(.y ~v))
            dz (or z `(.z ~v))]
       `(vector3 ~dx ~dy ~dz))))

user=> System.InvalidOperationException: Unable to resolve symbol: user=> in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> System.InvalidOperationException: Unable to resolve symbol: user=> in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	(let [{ x :x y :y z :z } changeset
            dx (or x `(.x ~v))
            dy (or y `(.y ~v))
            dz (or z `(.z ~v))]
       `(vector3 ~dx ~dy ~dz))))
#'user/vector3

user=> (vector3 (vector3 1 2 3) {:x 1})
(1.0, 2.0, 3.0)

user=> (vector3 (do (print "expensive") (vector3 1 2 3)) {:x 1})
expensiveexpensive(1.0, 2.0, 3.0)

user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	`(let [v# ~v
           { x# :x y# :y z# :z } changeset
            dx# (or x# (.x v#))
            dy# (or y# (.y v#))
            dz# (or z# (.z v#))]
       (vector3 dx# dy# dz#))))
#'user/vector3

user=> (vector3 (do (print "expensive") (vector3 1 2 3)) {:x 1})
System.InvalidOperationException: No such var: user/changeset
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	`(let [v# ~v
           { x# :x y# :y z# :z } ~changeset
            dx# (or x# (.x v#))
            dy# (or y# (.y v#))
            dz# (or z# (.z v#))]
       (vector3 dx# dy# dz#))))
#'user/vector3

user=> (vector3 (do (print "expensive") (vector3 1 2 3)) {:x 1})
expensive(1.0, 2.0, 3.0)

user=> (vector3 (do (print "expensive") (vector3 1 2 3)) {})
expensive(1.0, 2.0, 3.0)

user=> (defmacro vector3 
  ([x y z] `(Vector3. ~x ~y ~z))
  ([v changeset] 
  	`(let [v# ~v
           { x# :x y# :y z# :z } ~changeset
            dx# (or x# (.x v#) 0)
            dy# (or y# (.y v#) 0)
            dz# (or z# (.z v#) 0)]
       (Vector3. dx# dy# dz#))))
#'user/vector3

user=> (vector3 (do (print "expensive") (vector3 1 2 3)) {})
expensive(1.0, 2.0, 3.0)

user=> (match 1 1 10)
System.InvalidOperationException: Unable to resolve symbol: match in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (symbol? :arcadi)
false

user=> (keyword? :arcadi)
true

user=> (type (vector3 1 1 1))
System.InvalidOperationException: Unable to resolve symbol: vector3 in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (type (UnityEngine/Vector3. 1 1 1))
System.InvalidOperationException: Unable to find static field: Vector3. in 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (type (Vector3. 1 1 1))
UnityEngine.Vector3

user=> (defmacro vector3 
  ([v & changeset] 
  	`(let [v# ~v
           chset# ~changeset]
        (if (= UnityEngine.Vector3 (type v#))
            (let [{ x# :x y# :y z# :z } chset#
                 dx# (or x# (.x v#) 0)
                 dy# (or y# (.y v#) 0)
                 dz# (or z# (.z v#) 0)]
              (Vector3. dx# dy# dz#)))
            (Vector3. v# (first chset#) (second chset#))))))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro vector3 [& args] 
  `(let [args# ~args]
       (if (= UnityEngine.Vector3 (type args#))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(Vector3. %1 %2 %3) args#)))
#'user/vector3

user=> (vector3 1 2 3)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19003__19016.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# ~args]
       (if (= Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(Vector3. %1 %2 %3) args#)))
#'user/vector3

user=> (vector3 1 2 3)
System.InvalidOperationException: No such var: user/Vector3
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 [& args] 
  `(let [args# ~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(Vector3. %1 %2 %3) args#)))
#'user/vector3

user=> (vector3 1 2 3)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19121__19134.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# ~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))
#'user/vector3

user=> (vector3 1 2 3)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19187__19200.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# ~args]
       (if false;(= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))
#'user/vector3

user=> (vector3 1 2 3)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19253__19266.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (apply #(UnityEngine.Vector3. %1 %2 %3) '(1 2 3))
(1.0, 2.0, 3.0)

user=> (defmacro vector3 [& args] 
  `(let [args# ~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          '(args#)))
#'user/vector3

user=> (apply #(UnityEngine.Vector3. %1 %2 %3) '(1 2 3))
(1.0, 2.0, 3.0)

user=> (vector3 1 2 3)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19361__19367.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# ~args]
       (if false;(= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          '(args#)))
#'user/vector3

user=> (vector3 1 2 3)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__19414__19420.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# ~args]
       ;(if false;(= UnityEngine.Vector3 (type (first args#)))
       ;   ;; Vector3 and hashmap impl
       ;   (let [v# (first args#)
       ;         { x# :x y# :y z# :z } (apply hash-map (rest args#))
       ;         dx# (or x# (.x v#) 0)
       ;         dy# (or y# (.y v#) 0)
       ;         dz# (or z# (.z v#) 0)]
       ;     (UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          '(args#)))
#'user/vector3

user=> (vector3 1 2 3)

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro vector3 [& args] 
  `(let [args# `'~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))
#'user/vector3

user=> T ##
#############
System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> (vector3 1 2 3)
System.InvalidOperationException: No such var: user/args
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))
#'user/vector3

user=> (vector3 1 2 3)
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
(1.0, 2.0, 3.0)

user=> (vector3 (vector3 1 1 1) :x 1 :y 2 :z 3)
clojure.lang.ArityException: Wrong number of args (7) passed to: user/eval--19645/fn--19651--19655
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4, System.Object arg5, System.Object arg6, System.Object arg7) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19645__19658.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (vector3 (vector3 1 1 1) :x 1 :y 2 :z)
clojure.lang.ArityException: Wrong number of args (6) passed to: user/eval--19670/fn--19676--19680
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4, System.Object arg5, System.Object arg6) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19670__19683.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                ]'args;dx# (or x# (.x v#) 0)
                ;dy# (or y# (.y v#) 0)
                ;dz# (or z# (.z v#) 0)]
            ;(UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))
; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> 
***Repl Killed***
(defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                ]'args);dx# (or x# (.x v#) 0)
                ;dy# (or y# (.y v#) 0)
                ;dz# (or z# (.z v#) 0)]
            ;(UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))

user=> System.InvalidOperationException: Unable to resolve symbol: ***Repl in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                ]'args);dx# (or x# (.x v#) 0)
                ;dy# (or y# (.y v#) 0)
                ;dz# (or z# (.z v#) 0)]
            ;(UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> 

user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                ]'args);dx# (or x# (.x v#) 0)
                ;dy# (or y# (.y v#) 0)
                ;dz# (or z# (.z v#) 0)]
            ;(UnityEngine.Vector3. dx# dy# dz#)))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            'v#(comment (UnityEngine.Vector3. dx# dy# dz#))))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#)))
#'user/vector3

user=> (vector3 (vector3 1 1 1) :x 1 :y 2 :z :3)
System.ArgumentException: Invalid token: :3
  at clojure.lang.LispReader.InterpretToken (System.String rawToken, System.String token, System.String mask) [0x00000] in <filename unknown>:0 
  at clojure.lang.LispReader.read (clojure.lang.PushbackTextReader r, Boolean eofIsError, System.Object eofValue, Boolean isRecursive) [0x00000] in <filename unknown>:0 
user=> (vector3 (vector3 1 1 1) :x 1 :y 2 :z 3)
clojure.lang.ArityException: Wrong number of args (7) passed to: user/eval--19874/fn--19880--19884
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4, System.Object arg5, System.Object arg6, System.Object arg7) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19874__19887.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (vector3 (vector3 1 1 1) :x 1 :y 2 :z 3)
clojure.lang.ArityException: Wrong number of args (7) passed to: user/eval--19899/fn--19905--19909
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4, System.Object arg5, System.Object arg6, System.Object arg7) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19899__19912.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#))))
#'user/vector3

user=> (vector3 (vector3 1 1 1) :x 1 :y 2 :z 3)
clojure.lang.ArityException: Wrong number of args (7) passed to: user/eval--19965/fn--19971--19975
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4, System.Object arg5, System.Object arg6, System.Object arg7) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__19965__19978.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if false;(= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#))))
#'user/vector3

user=> (vector3 (vector3 1 1 1) :x 1 :y 2 :z 3)
clojure.lang.ArityException: Wrong number of args (7) passed to: user/eval--20031/fn--20037--20041
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4, System.Object arg5, System.Object arg6, System.Object arg7) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__20031__20044.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (vector3 (vector3 1 1 1))
clojure.lang.ArityException: Wrong number of args (1) passed to: user/eval--20056/fn--20062--20066
  at clojure.lang.AFn.invoke (System.Object arg1) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__20056__20069.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (vector3 1 1 1)
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
(1.0, 1.0, 1.0)

user=> 

user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if true;(= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#))))
#'user/vector3

user=> (vector3 1 1 1)
System.MissingMethodException: Cannot find instance field/property/member name x
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$eval__20147__20160.__interop_x20162 (object) <IL 0x0000e, 0x0004a>
at user$eval__20147__20160.invoke () <IL 0x00157, 0x00c24>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (vector3 1 1 1)
System.MissingMethodException: Cannot find instance field/property/member name x
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$eval__20172__20185.__interop_x20187 (object) <IL 0x0000e, 0x0004a>
at user$eval__20172__20185.invoke () <IL 0x00157, 0x00c24>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (vector3 (vector3 1 1 1))
System.MissingMethodException: Cannot find instance field/property/member name x
at (wrapper dynamic-method) object.CallSite.Target (System.Runtime.CompilerServices.Closure,System.Runtime.CompilerServices.CallSite,object) <IL 0x0002d, 0x0008e>
at System.Dynamic.UpdateDelegates.UpdateAndExecute1<object, object> (System.Runtime.CompilerServices.CallSite,object) <IL 0x0010c, 0x00645>
at user$eval__20197__20210.__interop_x20212 (object) <IL 0x0000e, 0x0004a>
at user$eval__20197__20210.invoke () <IL 0x00157, 0x00c24>
at clojure.lang.Compiler.eval (object) <IL 0x001f9, 0x00e55>
at clojure.lang.Compiler.eval (object) <IL 0x00167, 0x009ba>
at clojure/core$eval__2941.invoke (object) <IL 0x00006, 0x00045>
at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () <IL 0x00207, 0x01b22>
at arcadia/repl$repl_eval_print__17752.invoke (object,object) <IL 0x0042b, 0x04340>
at arcadia/repl$repl_eval_string__17768.invoke (object,object) <IL 0x00049, 0x002da>
at arcadia/repl$repl_eval_string__17768.invoke (object) <IL 0x0001a, 0x00110>
at arcadia/repl$eval_queue$fn__17776__17780.invoke () <IL 0x00015, 0x00102>

user=> (defmacro vector3 [& args] 
  `(let [args# (list ~args)]
       (if true;(= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#))))
#'user/vector3

user=> (vector3 (vector3 1 1 1))
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__20263__20284.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> 

user=> (vector3 1 1 1)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__20299__20312.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (list 1 2 3)
(1 2 3)

user=> (defmacro vector3 [& args] 
  `(let [args# (list ~args)]
       (if false;(= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#))))
#'user/vector3

user=> (vector3 1 1 1)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__20379__20392.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> 
(defmacro vector3 [& args] 
  `(let [args# 'args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#))))

user=> #'user/vector3

user=> (vector3 1 1 1)
System.ArgumentException: Don't know how to create ISeq from: clojure.lang.Symbol
  at clojure.lang.RT.seqFrom (System.Object coll) [0x00000] in <filename unknown>:0 
  at clojure.lang.RT.seq (System.Object coll) [0x00000] in <filename unknown>:0 
  at clojure.lang.RT.first (System.Object x) [0x00000] in <filename unknown>:0 
  at clojure/core$first__24.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__20445__20458.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type (first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#))))
#'user/vector3

user=> (vector3 1 1 1)
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property x can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property y can't be resolved (target class is unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - reference to field/property z can't be resolved (target class is unknown).
(1.0, 1.0, 1.0)

user=> (vector3 (vector3 1 1 1))
clojure.lang.ArityException: Wrong number of args (1) passed to: user/eval--20536/fn--20542--20546
  at clojure.lang.AFn.invoke (System.Object arg1) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__20536__20549.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3 [& args] 
  `(let [args# '~args]
       (if (= UnityEngine.Vector3 (type ~(first args#)))
          ;; Vector3 and hashmap impl
          (let [v# (first args#)
                { x# :x y# :y z# :z } (apply hash-map (rest args#))
                dx# (or x# (.x v#) 0)
                dy# (or y# (.y v#) 0)
                dz# (or z# (.z v#) 0)]
            (UnityEngine.Vector3. dx# dy# dz#))
          ;; The float impl
          (apply #(UnityEngine.Vector3. %1 %2 %3) args#))))
System.InvalidOperationException: Unable to resolve symbol: args# in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> (vector3 (vector3 1 1 1))
clojure.lang.ArityException: Wrong number of args (1) passed to: user/eval--20585/fn--20591--20595
  at clojure.lang.AFn.invoke (System.Object arg1) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.ApplyToHelper (IFn fn, ISeq argList) [0x00000] in <filename unknown>:0 
  at clojure.lang.AFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__20585__20598.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro vector3  [x y z] `(Vector3. ~x ~y ~z))
(defmacro v3update [v changeset] 
  `(let [v# ~v
         { x# :x y# :y z# :z } ~changeset
          dx# (or x# (.x v#) 0)
          dy# (or y# (.y v#) 0)
          dz# (or z# (.z v#) 0)]
     (Vector3. dx# dy# dz#))))
#'user/vector3

user=> (defmacro v3update [v & changeset] 
  `(let [v# ~v
         { x# :x y# :y z# :z } (hash-map ~changeset)
          dx# (or x# (.x v#) 0)
          dy# (or y# (.y v#) 0)
          dz# (or z# (.z v#) 0)]
     (Vector3. dx# dy# dz#))))

***Repl Killed***

#############
## RESTAR; Clojure Unity REPL
; Clojure 1.7.0-master-SNAPSHOT
; Unity 4.6.1f1 (d1db7a1b5196)
Reflection warning, NO_SOURCE_PATH:0:0 - call to method GetMethod on System.Type can't be resolved (argument types: System.String, unknown).
Reflection warning, NO_SOURCE_PATH:0:0 - call to method Invoke can't be resolved (target class is unknown).
; Mono 2.6.5 (tarball)


user=> T ##
#############

System.InvalidOperationException: Unable to resolve symbol: T in this context
  at clojure.lang.Compiler.ResolveIn (clojure.lang.Namespace n, clojure.lang.Symbol symbol, Boolean allowPrivate) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Resolve (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.AnalyzeSymbol (clojure.lang.Symbol symbol) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.Analyze (clojure.lang.CljCompiler.Ast.ParserContext pcontext, System.Object form, System.String name) [0x00000] in <filename unknown>:0 , compiling: (NO_SOURCE_PATH:0:0)
user=> 
user=> 
user=> (defmacro vector3  [x y z] `(Vector3. ~x ~y ~z))
(defmacro v3update [v & changeset] 
  `(let [v# ~v
         { x# :x y# :y z# :z } (hash-map ~changeset)
          dx# (or x# (.x v#) 0)
          dy# (or y# (.y v#) 0)
          dz# (or z# (.z v#) 0)]
     (Vector3. dx# dy# dz#)))
#'user/vector3

user=> #'user/v3update

user=> (vector3 1 1 1)
(1.0, 1.0, 1.0)

user=> (v3update (vector3 1 1 1))
System.ArgumentException: No value supplied for key: 
  at clojure.lang.PersistentHashMap.create (ISeq items) [0x00000] in <filename unknown>:0 
  at clojure/core$hash_map__193.doInvoke (System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.RestFn.invoke (System.Object arg1) [0x00000] in <filename unknown>:0 
  at user$eval__20759__20764.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro v3update [v & changeset] 
  `(let [v# ~v
         { x# :x y# :y z# :z } (hash-map :map true ~changeset)
          dx# (or x# (.x v#) 0)
          dy# (or y# (.y v#) 0)
          dz# (or z# (.z v#) 0)]
     (Vector3. dx# dy# dz#)))
#'user/v3update

user=> (v3update (vector3 1 1 1))
System.ArgumentException: No value supplied for key: 
  at clojure.lang.PersistentHashMap.create (ISeq items) [0x00000] in <filename unknown>:0 
  at clojure/core$hash_map__193.doInvoke (System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.RestFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3) [0x00000] in <filename unknown>:0 
  at user$eval__20811__20816.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (v3update (vector3 1 1 1) :x 1 :y :z 3)
clojure.lang.ArityException: Wrong number of args (4) passed to: Keyword
  at clojure.lang.AFn.invoke (System.Object arg1, System.Object arg2, System.Object arg3, System.Object arg4) [0x00000] in <filename unknown>:0 
  at user$eval__20829__20834.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro v3update [v & changeset] 
  `(let [v# ~v
         { x# :x y# :y z# :z } (apply hash-map '~changeset)
          dx# (or x# (.x v#) 0)
          dy# (or y# (.y v#) 0)
          dz# (or z# (.z v#) 0)]
     (Vector3. dx# dy# dz#)))
#'user/v3update

user=> (v3update (vector3 1 1 1) :x 1 :y :z 3)
System.ArgumentException: No value supplied for key: 3
  at clojure.lang.PersistentHashMap.create (ISeq items) [0x00000] in <filename unknown>:0 
  at clojure/core$hash_map__193.doInvoke (System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.RestFn.applyTo (ISeq arglist) [0x00000] in <filename unknown>:0 
  at clojure/core$apply__453.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at user$eval__20881__20886.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro a [v & b] `(let [v# ~v b# (hash-map ~b)] b#))
#'user/a

user=> (a 1 :a 2)
System.ArgumentException: No value supplied for key: 
  at clojure.lang.PersistentHashMap.create (ISeq items) [0x00000] in <filename unknown>:0 
  at clojure/core$hash_map__193.doInvoke (System.Object ) [0x00000] in <filename unknown>:0 
  at clojure.lang.RestFn.invoke (System.Object arg1) [0x00000] in <filename unknown>:0 
  at user$eval__20927__20932.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (a :a 2)
System.InvalidCastException: Cannot cast from source type to destination type.
  at user$eval__20941__20946.invoke () [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure.lang.Compiler.eval (System.Object form) [0x00000] in <filename unknown>:0 
  at clojure/core$eval__2941.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print$fn__17745__17749.invoke () [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_print__17752.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object , System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$repl_eval_string__17768.invoke (System.Object ) [0x00000] in <filename unknown>:0 
  at arcadia/repl$eval_queue$fn__17776__17780.invoke () [0x00000] in <filename unknown>:0 
user=> (defmacro a [v & b] `(let [v# ~v b# '~b] b#))
#'user/a

user=> (a :a 2)
(2)

user=> (a 1 :a 2)
(:a 2)

user=> (hash-map :a 1)
{:a 1}

user=> (defmacro a [v & b] `(let [v# ~v b# '~b] '(hash-map ~b)))
#'user/a

user=> (a 1 :a 2)
(clojure.core/hash-map (:a 2))

user=> (defmacro a [v & b] `(let [v# ~v b# '~b] '(apply hash-map ~b)))
#'user/a

user=> (a 1 :a 2)
(clojure.core/apply clojure.core/hash-map (:a 2))

user=> (defmacro a [v & b] `(let [v# ~v b# '(apply hash-map ~b)] b#))