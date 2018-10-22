(ns sandbox.protocols
  (:require [clojure.test :refer [with-test is deftest]]))

(defprotocol P
   (foo1 [this])
   (bar-me [this] [this y] [this y z]))

(deftype Foo [a b c]
  P
  (foo1 [this] a)
  (bar-me [this] b)
  (bar-me [this x] (+ c x))
  (bar-me [this x y] (+ a b c x y)))


(bar-me (Foo. 1 2 3) 50 42)



(defprotocol Greet
  (sayHello [this]))

(defrecord Person [firstName lastName]
  Greet
  (sayHello [this] (print "Hello, my name is " firstName)))

(sayHello (Person. "Tal" "Binyamini"))


