# WebMan

A [re-frame](https://github.com/Day8/re-frame) application designed to build different websites sharing same structure
but different content using one build process.

## How it works ?
Basically Webman created using several `clojure` macros which parse and provide the necessary information for the
`clojurescript/re-frame` application in compile time based on the website.

Clojure macros look for a environment variable called `WEBMAN_WEBSITE` that contains the domain name of the website and load the configuration
for the given website from `./resources/websites/WEBSITE_WEBSITE.edn` and load the stylesheet from `./sass/WEBSITE_WEBSITE.sass`.

So basically in order to add support for a new website all you have to do is to create those two files and set the environment variable.

## Development Mode

If you use Emacs, navigate to a clojurescript file and start a figwheel REPL with `cider-jack-in-clojurescript` or (`C-c M-J`).


If you want to run the server on your terminal instead of Emacs just do as follows:

```
$ WEBMAN_WEBSITE=example.com lein figwheel dev
```

**NOTE**: replace `example.com` with the domain name of your website.

in bother cases you should be able to connect to [http://localhost:3449](http://localhost:3449)

I highly recommend to use a development environment such as `cider` for emacs or `cursive` so you can
take leavrage of auto compiling the clojure macros as well.

### Compile css:

Compile css file automatically.

```
WEBMAN_WEBSITE=example.com lein sass
```

**NOTE**: replace `example.com` with the domain name of your website.

### Run tests:

```
lein clean
lein doo phantom test once
```

The above command assumes that you have [phantomjs](https://www.npmjs.com/package/phantomjs) installed. However, please note that [doo](https://github.com/bensu/doo) can be configured to run cljs.test in many other JS environments (chrome, ie, safari, opera, slimer, node, rhino, or nashorn).

## Production Build


To compile clojurescript to javascript:

```
WEBMAN_WEBSITE=example.com lein clean
WEBMAN_WEBSITE=example.com lein sass-compile
WEBMAN_WEBSITE=example.com lein cljsbuild once min
```

### Deployment
Pushing to `stable` branch will triggers the CI jobs for building and deploying all the websites.
