package users

import "mts/graph/model"

var defaultBalance = 100

type Store map[string]*model.User

var Users = Store{
	"1": {
		ID:         "1",
		Name:       "Roman",
		Balance:    &defaultBalance,
		PhoneNum:   "+375447534067",
		Avatar:     "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeqR380Xp18gOc03TuDbnSu8NP51nZAxC_V-VNeEaSTQ&s",
		CardNumber: "1234 1234 1234 1234",
	},
	"2": {
		ID:         "2",
		Name:       "Nastya",
		Balance:    &defaultBalance,
		PhoneNum:   "+375333932056",
		Avatar:     "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmOHdtyc8zOab__8yW-55ZR9cAFWpesphHdP4ZqzLuezCY5Yj_gEoeDUvwwzLZilFn0Cw&usqp=CAU",
		CardNumber: "1234 1234 1234 1234",
	},
	"3": {
		ID:         "3",
		Name:       "Robert",
		Balance:    &defaultBalance,
		PhoneNum:   "+375447024765",
		Avatar:     "https://beebom.com/wp-content/uploads/2022/02/Featured.jpg?w=750&quality=75",
		CardNumber: "1234 1234 1234 1234",
	},
	"4": {
		ID:         "4",
		Name:       "Sasha",
		Balance:    &defaultBalance,
		PhoneNum:   "+375440909088",
		Avatar:     "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDNXEYbrB_K3hXQcOLwqIHNl4Nuw83m4-B11x9GosSQb3qczecNLh890D9tQv-CqEk3Pg&usqp=CAU",
		CardNumber: "1234 1234 1234 1234",
	},
	"5": {
		ID:         "5",
		Name:       "Diana",
		Balance:    &defaultBalance,
		PhoneNum:   "+375446677889",
		Avatar:     "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLUf3TdjjL1GbjJdcDIsqzlxPfYjGh9LHaWg&s",
		CardNumber: "1234 1234 1234 1234",
	},
}
