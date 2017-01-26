(ns word.core
  (:require [clojure.string :as string]))

(defn wrap [phrase columns]
  (if (= nil phrase)
    ""
    (if (> (count phrase) columns)
      (let [whitespace-pos (string/index-of phrase " ")]
        (if whitespace-pos
          (string/join
            (cons (subs phrase 0 whitespace-pos)
                  (cons "\n"
                        (wrap (subs phrase (inc whitespace-pos)) columns))))
          (string/join
            (cons (subs phrase 0 columns)
                  (cons "\n"
                        (wrap (subs phrase columns) columns))))))
      phrase)))
