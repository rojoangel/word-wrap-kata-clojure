(ns word.core-test
  (:use midje.sweet)
  (:use [word.core]))

(facts "about wrap"
       (fact "wraps empty as empty"
             (wrap nil 10) => ""
             (wrap "" 10) => ""))
