;; Webman - Static website generator
;; Copyright (C) 2018-2019  Sameer Rahmani <lxsameer@gnu.org>

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

(ns webman.config.readers)

(defn asset
  "Reader function for `#asset` edn tag which append a random querystring
  to the assets url."
  [url]
  (format "%s?%s" url (int (* 100000000 (rand)))))

;; EDN Readers
(def edn-readers
  {'asset asset})
