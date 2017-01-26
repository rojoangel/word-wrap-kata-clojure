(ns word.core)

(defn wrap [phrase columns]
  (if (= nil phrase)
    ""
    phrase))
