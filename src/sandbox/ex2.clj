(ns sandbox.ex2
  (:require [clojure.test :refer [with-test is deftest]]))

;Compose a string from the letters whose ascii values are odd numbers
(let [lst (range 97 123)
      f-lst (filter odd? lst)
      m-lst (map char f-lst)]
  (reduce str "" m-lst))

(identity (as-> (range 97 123) $
                (filter odd? $)
                (map char $)
                (reduce str "" $)))

(->> (range 97 123)
     (filter odd?)
     (map char)
     (reduce str ""))


;frequencies
(with-test (defn my-freq [coll]
             (reduce (fn [res c]
                       (assoc res c (+ (get res c 0) 1))
                       )
                     {}
                     coll))
           (is (= (my-freq ['a 'b 'a 'a]) '{a 3, b 1})))


;reduce-by-key
(with-test
  (defn reduce-by-key [f lst]
    (->> (reduce (fn [res [key val]]
                   (assoc res key (f val (get res key 0))))
                 {}
                 lst
                 )
         (into ())))
  (is (= (reduce-by-key + '((a 1) (a 3) (b 1) (a 5) (b 6))) '((a 9) (b 7)))))


((comp inc inc :b) {:a 1 :b 2 :c 3 :d 4})


;comp-2
(defn comp-2 [f g]
  (fn
    ([] (f (g)))
    ([x] (f (g x)))
    ([x y] (f (g x y)))
    ([x y z] (f (g x y z)))
    ([x y z & args] (f (apply g x y z args)))))

((comp-2 inc inc ) 2)

((comp inc ) 1)


;inc-seq
(with-test
  (defn inc-seq [s]
    (if (empty? s)
      '()
      (cons (inc (first s))
            (inc-seq (rest s)))))
  (is (= (take 10 (inc-seq (range 100))) '(1 2 3 4 5 6 7 8 9 10))))

;numbers
(defn numbers []
  (lazy-seq
    (partial > 0))
  )

;present
(with-test
  (defn present? [lst x]
    (loop [lst lst
           x x]
      (if (empty? lst)
        false
        (if (= x (first lst))
          true
          (recur (rest lst) x)))
      ))
  (is (= (present? '(1 2 3) 2) true)))


;concat-seqs
(with-test (defn concat-seqs [seqs]
             (->> (loop [seqs seqs res []]
                    (if (empty? seqs)
                      res
                      (recur (rest seqs) (concat res (first seqs)))))
                  (into [])))
           (is (= (concat-seqs [[1 2 3] [4 5 6] [7 8 9]]) [1 2 3 4 5 6 7 8 9])))

(reduce-by-key + '((a 1) (a 3) (b 1) (a 5) (b 6)))


;Destruction

;destructing a map inside a vector
(let [[{:keys [a b]}] [{:a 1 :b 2}]] b)




