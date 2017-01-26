(ns word.core
  (:require [clojure.string :as string]))

(defn wrap [phrase columns]
  (if (= nil phrase)
    ""
    (if (> (count phrase) columns)
      (string/join
        (cons (subs phrase 0 columns)
              (cons "\n"
                    (wrap (subs phrase columns) columns))))
      phrase)))
