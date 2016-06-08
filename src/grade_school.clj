 (ns grade-school)

 (defn add [db name grade]
  (update db grade (comp vec conj) name))

 (defn grade [db grade]
  (get db grade []))