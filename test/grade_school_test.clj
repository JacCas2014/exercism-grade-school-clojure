(ns grade-school-test
  (:require grade-school)
  (:use midje.sweet))

(def db {})

(fact "add-student"
      (grade-school/add db "Aimee" 2) => {2 ["Aimee"]})

(fact "add-more-students-in-same-class"
      (-> db
          (grade-school/add "James" 2)
          (grade-school/add "Blair" 2)
          (grade-school/add "Paul" 2)) => {2 ["James", "Blair", "Paul"]})

(fact "add-students-to-different-grades"
      (-> db
          (grade-school/add "Chelsea" 3)
          (grade-school/add "Logan" 7)) => {3 ["Chelsea"] 7 ["Logan"]})

(fact "get-students-in-a-grade"
      (-> db
          (grade-school/add "Franklin" 5)
          (grade-school/add "Bradley" 5)
          (grade-school/add "Jeff" 1)
          (grade-school/grade 5)) => ["Franklin", "Bradley"])

(fact "get-students-in-a-non-existant-grade"
      (grade-school/grade db 1) => [])

(future-fact "sorted-grade-school"
      (-> db
          (grade-school/add "Jennifer" 4)
          (grade-school/add "Kareem" 6)
          (grade-school/add "Christopher" 4)
          (grade-school/add "Kyle" 3)
          (grade-school/sorted)) => (sorted-map 3 ["Kyle"]
                                                4 ["Christopher" "Jennifer"]
                                                6 ["Kareem"]))

(future-fact "sorted-grade_school-keys-sorted"
      (-> db
          (grade-school/add "Jennifer" 4)
          (grade-school/add "Kareem" 6)
          (grade-school/add "Christopher" 4)
          (grade-school/add "Kyle" 3)
          (grade-school/sorted)
          (keys)) => [3 4 6])
