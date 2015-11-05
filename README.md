# A master json project (to rule them all)

This projects puts these json projects under one umbrella:

 * [jawn](https://github.com/non/jawn)
 * [argonaut](https://github.com/argonaut-io/argonaut)
 * [rojoma](https://github.com/rjmac/rojoma-json)
 * [spray-json](http://github.com/spray/spray-json)
 * [json4s](https://github.com/json4s/json4s)
 * [json4s-ast](https://github.com/json4s/json4s-ast)

and then adds a source dependency to json4s-ast to facilitate the transition of the ASTs of those projects
to the future common one, `json4s-ast`. The master project automatically adds a source dependency to `json4s-ast`
to each of the other projects so that one can easily experiment with changes.

## How to checkout

```
git clone --recursive https://github.com/jrudolph/json-all
```

should checkout all of the submodules.

## How to switch projects in sbt

That's a bit clunky because you need to add the full path to the build in question, try

```
> project <TAB>
```

to choose one of the completions.