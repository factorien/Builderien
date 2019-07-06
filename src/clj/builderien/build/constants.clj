;; Builderien - Static website generator
;; Copyright (C) 2018-2019  Factorien Team

;; This program is free software; you can redistribute it and/or
;; modify it under the terms of the GNU General Public License
;; as published by the Free Software Foundation; either version 2
;; of the License, or any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.

(ns builderien.build.constants)

(def copy-right-header ";; Builderien - Static website generator
;; Copyright (C) 2018-2019  Factorien Team

;; This program is free software; you can redistribute it and/or
;; modify it under the terms of the GNU General Public License
;; as published by the Free Software Foundation; either version 2
;; of the License, or any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.
")

(def binding-warning-header "
;; WARNING ====================================================================
;;
;; This file is automatically generated by Builderien.
;; Do not modify it because Builderien is going to replace it anyway.
;;
;; If you want to regenerate this file use `builderien.build/create-bindings`
;; function vial REPL or directly through your build tool. For example
;; you can use `shadow-cljs` to run in like:
;;
;; $ shadow-cljs run  builderien.build/create-bindings
;;
;; For more information please refer to `create-bindings`'s docstring.
;;
;; ============================================================================
")


(def binding-ns-form-template "(ns builderien.views.bindings
  (:require
NS))
")

(def default-mobile-manifest
  {:expo
   {:slug "Builderien"
    :splash
    {:image "./resources/public/images/splash.png"
     :resizeMode "contain"
     :backgroundColor "#ffffff"}
    :ios {:supportsTablet true}
    :sdkVersion "32.0.0"
    :name "Builderien"
    :entryPoint "./target/index.js"
    :privacy "public"
    :assetBundlePatterns ["**/*"]
    :orientation "portrait"
    :icon "./resources/public/images/icon.png"
    :version "1.0.0"
    :updates {:fallbackToCacheTimeout 0}
    :platforms ["ios" "android"]}})
