 (ns grade-school)

 (defn add [db name grade]
  (update db grade (comp vec conj) name))

 (defn grade [db grade]
  (get db grade []))

 (defn sorted [db]
  (let [db-with-sorted-vals (map #(update-in % [1] sort) db)]
   (into (sorted-map) db-with-sorted-vals)))