package com.example.compose.repository

import com.example.compose.model.Movie

class MovieRepository {
    fun getAllData(): List<Movie>{
        return listOf(
            Movie(
                id = 0,
                tittle = "Miss Marvel",
                year = 2021,
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                rate = 9
            ),
            Movie(
                id = 1,
                tittle = "Sonic the Hedgehog 2",
                year = 2022,
                description = "After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero. His test comes when Dr. Robotnik returns, this time with a new partner, Knuckles, in search for an emerald that has the power to destroy civilizations.",
                rate = 8
            ),
            Movie(
                id = 2,
                tittle = "Morbius",
                year = 2022,
                description = "Dangerously ill with a rare blood disorder, and determined to save others suffering his same fate, Dr. Michael Morbius attempts a desperate gamble. What at first appears to be a radical success soon reveals itself to be a remedy potentially worse than the disease.",
                rate = 7
            ),
            Movie(
                id = 3,
                tittle = "The Meg",
                year = 2020,
                description = "A deep sea submersible pilot revisits his past fears in the Mariana Trench, and accidentally unleashes the seventy foot ancestor of the Great White Shark believed to be extinct.",
                rate = 8
            ),
            Movie(
                id = 4,
                tittle = "Jurassic World: Fallen Kingdom",
                year = 2021,
                description = "Three years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the isla Nublar, so Claire Dearing, the former park manager, recruits Owen Grady to help prevent the extinction of the dinosaurs once again.",
                rate = 10
            ),
            Movie(
                id = 5,
                tittle = "Pan",
                year = 2015,
                description = "Living a bleak existence at a London orphanage, 12-year-old Peter finds himself whisked away to the fantastical world of Neverland. Adventure awaits as he meets new friend James Hook and the warrior Tiger Lily. They must band together to save Neverland from the ruthless pirate Blackbeard. Along the way, the rebellious and mischievous boy discovers his true destiny, becoming the hero forever known as Peter Pan.",
                rate = 9
            )
        )
    }
}