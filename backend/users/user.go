package users

type User struct {
	Id       string `json:"id"`
	Name     string `json:"name"`
	PhoneNum string `json:"phone_num"`
	Avatar   string `json:"avatar"`
	Balance  int    `json:"balance"`
}

var Users = map[string]*User{
	"1": {
		Name:     "Roman",
		Balance:  100,
		PhoneNum: "+375447534067",
		Avatar:   "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeqR380Xp18gOc03TuDbnSu8NP51nZAxC_V-VNeEaSTQ&s",
	},
	"2": {
		Name:     "Nastya",
		Balance:  100,
		PhoneNum: "+375333932056",
		Avatar:   "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmOHdtyc8zOab__8yW-55ZR9cAFWpesphHdP4ZqzLuezCY5Yj_gEoeDUvwwzLZilFn0Cw&usqp=CAU",
	},
	"3": {
		Name:     "Robert",
		Balance:  100,
		PhoneNum: "+375447024765",
		Avatar:   "https://beebom.com/wp-content/uploads/2022/02/Featured.jpg?w=750&quality=75",
	},
	"4": {
		Name:     "Sasha",
		Balance:  100,
		PhoneNum: "+375440909088",
		Avatar:   "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDNXEYbrB_K3hXQcOLwqIHNl4Nuw83m4-B11x9GosSQb3qczecNLh890D9tQv-CqEk3Pg&usqp=CAU",
	},
	"5": {
		Name:     "Diana",
		Balance:  100,
		PhoneNum: "+375446677889",
		Avatar:   "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLUf3TdjjL1GbjJdcDIsqzlxPfYjGh9LHaWg&s",
	},
}
