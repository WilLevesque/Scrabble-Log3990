package com.example.polyscrabbleclient.ui.theme

// Authentication System
const val email_string = "Courriel"
const val userName_string = "Pseudonyme"
const val password_string = "Mot de passe"
const val create_account = "Créer un compte"
const val connection = "Connexion"
const val connect = "Se connecter"
const val signUp = "S'inscrire"
const val disconnection = "Déconnexion"
const val gamePage = "Page de jeu Scrabble"
const val lobbyPage = "Créer/Rejoindre une partie"
const val no_Account = "Vous n'avez pas de compte?"

// Account
const val my_profil = "Mon profil"
const val my_account = "Mon compte"
const val save = "Sauvegarder"
const val avatars = "Avatar"
const val user_statisics = "Statistiques"
const val game_statistics = "Statistiques de jeux"
const val connection_history = "Historique de connexions"
const val my_statistics = "Mes statistiques"
const val gamePlayed = "Parties jouées"
const val gameWinned = "Parties gagnées"
const val averagePointsPerGame = "Moyenne de points par partie"
const val averageTimePerGame = "Moyenne de temps par partie"
const val date = "Date"
const val connection_type = "Type de connexions"
// Errors
const val missing_field = "Veuillez remplir tous les champs."
const val invalid_password = "Mot de passe invalide."
const val invalid_email = "Aucun utilisateur n'a cet email."
const val wrong_form_email = "Veuillez saisir un e-mail valide."
val invalid_username_creation: (minLength : Int, maxLength: Int) -> String = { minLength, maxLength -> "Entrer un pseudonyme entre $minLength et $maxLength caractères"}
const val userName_not_unique = "Un compte utilise déjà ce pseudonyme"
const val email_not_unique = "Un compte utilise déjà cet e-mail."
val invalid_password_creation : (minLength : Int, maxLength: Int) -> String = { minLength, maxLength -> "Entrer un mot de passe entre $minLength et $maxLength caractères"}
const val already_auth = "Vous êtes déjà connecté sur un autre client"

// GameScreen
val lettersRemainingFR: (count: Int) -> String = { count -> "$count lettres restantes"}
val lettersRemainingEN: (count: Int) -> String = { count -> "$count letters remaining"}
const val passButtonFR = "Passer"
const val placeButtonFR = "Placer"
const val exchangeButtonFR = "Échanger"
const val cancelButtonFR = "Annuler"
const val quitButtonFR = "Abandonner la partie"
const val leaveButtonFR = "Quitter"

// GameCreation
const val new_game_creation = "Nouvelle Partie Multijoueurs"
const val time_per_turn = "Temps par tour"
const val random_bonus = "Bonus aléatoire"
const val cancel = "Annuler"
const val create_game = "Créer la partie"
const val number_of_player ="Nombre de joueurs"
const val create_game_multiplayers = "Créer une partie multijoueurs"
const val join_game_multiplayers = "Joindre une partie multijoueurs"
const val new_game = "Nouvelle Partie"
const val choose_bot_difficulty = "Difficulté du joueur virtuel"
const val game_mode ="Mode de jeu"
const val classic = "Classique"
const val magic_cards = "Carte de pouvoirs"

// Lobby
const val joinGameButtonFR = "Rejoindre la partie"
const val joinGameButtonEN = "Join game"
const val launchGameButtonFR = "Démarrer la partie"
const val waitingForOtherPlayersFR = "En attente d'autres joueurs"
const val joinAGameFR = "Joindre une partie"
const val hostQuitGameFR = "L'hôte a quitté la partie"
const val Ok = "Ok"
val pendingGameIdFR: (id: String?) -> String = { id -> "Id de la partie: ${id ?: ""}"}



