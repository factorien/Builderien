(ns builderien.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [builderien.core-test]))

(doo-tests 'builderien.core-test)
