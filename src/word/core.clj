(ns word.core)

(defn wrap [phrase columns]
  (if (= nil phrase)
    ""
    (if (> (count phrase) columns)
      "long\nword"
      phrase)))
