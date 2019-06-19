# Builderien

A [re-frame](https://github.com/Day8/re-frame) application designed to build different websites sharing same structure
but different content using one build process.

## Dependencies
Global dependencies:

* NodeJS >= 9.x
* Yarn
* npx (`npm install -g npx`)
* OpenJDK >= 1.8
* rlwrap (Install via package manager, apt or brew)

After installing global dependencies install the local dependencies using `./bin/setup.sh`  as follows:

```
$ ./bin/setup.sh
```

**NOTE**: Make sure that you're not `root` user and you're in the root directory of the project.

Then you need to use `./bin/env.sh` to setup the `PATH` and some useful aliases to help you with your
development. **If you're not a contributor or just want to run the project you can skip this step.**

## How it works ?
Basically Builderien created using several `clojure` macros which parse the provided configuration file (in `edn`)
format and generate a  `clojurescript/re-frame` application in compile time based on the given configuration.

Clojure macros look for a environment variable called `BUIDERIEN_WEBSITE` that contains the domain name of the website
and load the configuration for the given website from `./resources/websites/WEBSITE_NAME.edn` and load the stylesheet
from `./sass/WEBSITE_NAME.sass`.

So basically in order to add support for a new website all you have to do is to create those two files and set the environment variable.

### Compile css:

Compile css file automatically.

```
BUIDERIEN_WEBSITE=example.com lein sass
```

**NOTE**: replace `example.com` with the domain name of your website.

## Production Build

To compile clojurescript to javascript:

```
BUIDERIEN_WEBSITE=example.com shadow-cljs release app
```

### Deployment
Pushing to `stable` branch will triggers the CI jobs for building and deploying all the websites.

### Editor integration

If you're using emacs make sure to add you NodeJS path to your `exec-path` as follows:


```lisp
;; In case of nodenv
(add-to-list 'exec-path "/home/user/.nodenv/shims/")
```
