(ns com.iraqidata.tasks.day-one)


;; 1. Write a function that takes one argument as input and prints that
;; argument.

(defn printarg [arg]
  (println arg))




;; 2. Write a function that adds `1` to a number only if the input is odd,
;; otherwise return `:error`.

;; Tip, use the `odd?` function to check if a number is odd.

(defn add-if-odd [n]
  (if (odd? n)
    (+ n 1)
    :error))



;; 3. Write a function that takes 3 arguments, `name`, `year-of-birth`, and
;; `current-year`. and returns a map with the following keys: `name`, `age`.

;; Example run
;; (function-name "Ali" 2001 2024) => {:name "Ali", :age 23}

(defn person-info [name year-of-birth, current-year]
  {:name name
   :age  (- current-year year-of-birth)})



;; 4. Write a function that takes the output of the above function and returns
;; `true` if the person is allowed to vote (assume the voting age is 18).

;; Example run
;; (function-name {:name "Ali", :age 23}) => true
;; (function-name "Ali" 2001 2024) => true
;; (function-name {:name "Abbas", :age 17}) => false

(defn can-vote [person]
  (>= (:age person) 18))



;; OPTIONAL FOR BONUS POINTS

;; 5. Modify the function from number 3 to not need the `current-year`.
;; Example run
;; (function-name "Ali" 2001) => {:name "Ali", :age 23}
;; If ran in 2025
;; (function-name "Ali" 2001) => {:name "Ali", :age 24}
(defn person-info-without-current [name year-of-birth]
  {:name name
   :age  (- (.getValue (java.time.Year/now)) year-of-birth)})

 (person-info-without-current "Ali" 2001)
