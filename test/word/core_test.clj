(ns word.core-test
  (:use midje.sweet)
  (:use [word.core]))

(facts "about wrap"
       (fact "wraps nil as string"
             (wrap nil 10) => ""))
