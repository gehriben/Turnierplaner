-- --------------------------------------------------------
-- Dummy Data - Turnierplaner
-- --------------------------------------------------------
use turnierplaner;
DELETE FROM `ranking_placement`;
DELETE FROM `ranking`;
DELETE FROM `game_points`;
DELETE FROM `encounter_tournament_participant`;
DELETE FROM `game_set`;
DELETE FROM `encounter`;
DELETE FROM `tournament_group`;
DELETE FROM `tournament_participant`;
DELETE FROM `participant`;
DELETE FROM `tournament`;

-- --------------------------------------------------------
-- Table tournament
-- --------------------------------------------------------

INSERT INTO `tournament` (`tournament_id`, `group_divider`, `group_definition`, `tournament_date`, `tournament_name`, `tournament_state`) VALUES
	(1, 2, 0, '2019-04-14', 'ZHAW Turnier', 0),
	(2, 2, 0, '2019-04-11', 'Grümpi Sitterdorf', 0),
	(3, 2, 0, '2019-12-12', 'Basketball', 0),
	(4, 2, 0, '2019-04-14', 'Fussball-Plauschturnier Frauenfeld', 0),
	(5, 2, 0, '2019-12-14', 'Grümpi Tägerwilen', 0),
	(6, 2, 0, '2020-03-21', 'Dörferturnier Buchberg', 0),
	(7, 2, 0, '2019-04-14', 'Grümpi Amriswil', 0),
	(8, 2, 0, '2019-12-14', 'Grümpi Hefenhofen', 0),
	(9, 2, 0, '2020-03-21', 'Tischtennis Turnier Romanshorn 2019', 0),
	(10, 2, 0, '2020-03-21', 'Unihockery Turnier Sulgen', 0),
	(11, 2, 0, '2019-04-14', 'Unihockery Turnier Frauenfeld', 0),
	(12, 2, 0, '2019-12-14', 'Tennis Turnier Aadorf', 0),
	(13, 2, 0, '2018-03-21', 'Tischtennis Turnier Romanshorn 2018', 0);

	-- --------------------------------------------------------
-- Table participant
-- --------------------------------------------------------	

INSERT INTO `participant` (`participant_id`, `participant_date_of_birth`, `participant_first_name`, `participant_last_name`, `participant_residence`, `participant_zip_code`) VALUES
	(1, '1996-12-17', 'Benjamin', 'Gehring', 'Buchberg', '8454'),
	(2, '1997-03-29', 'Sebastian', 'Spieler3', 'Altnau', '3000'),
	(3, '1997-03-20', 'Marco', 'Streller', 'Amriswil', '2000'),
	(4, '1995-03-21', 'Brian', 'Witdmann', 'Aadorf', '8500'),
	(5, '1997-03-29', 'Luca', 'Hochreutener', 'Rothenhausen', '4000'),
	(6, '2019-03-29', 'Alex', 'Lau', 'Schaffhausen', '1000'),
	(7, '1997-04-05', 'Tobias', 'Meier', 'Weinfelden', '5000'),
	(8, '2000-11-05', 'Reto', 'Mayer', 'Berg', '6000'),
	(9, '1997-04-11', 'Aleksandar', 'Radovic', 'Arbon', '8580'),
	(10, '1997-04-12', 'Bela', 'Gehring', 'Schaffhausen', '8454'),
	(11, '1995-03-12', 'Colin', 'Dreher', 'Tägerwilen', '8454'),
	(12, '1995-04-14', 'Lukas', 'Schnell', 'Kreuzlingen', '8512'),
	(13, '1997-01-05', 'Roman', 'Meier', 'Weinfelden', '5000'),
	(14, '1996-02-05', 'Reto', 'Mayer', 'Berg', '6000'),
	(15, '1997-03-11', 'Pascal', 'Radovic', 'Arbon', '8580'),
	(16, '1996-04-12', 'Yves', 'Gehring', 'Schaffhausen', '8454'),
	(17, '1997-08-12', 'Stefan', 'Witdmann', 'Tägerwilen', '8454'),
	(18, '1996-05-14', 'Lukas', 'Keller', 'Kreuzlingen', '8512'),
	(19, '1997-04-12', 'Bela', 'Gehring', 'Schaffhausen', '8454'),
	(20, '1995-03-12', 'Colin', 'Dreher', 'Tägerwilen', '8454'),
	(21, '1995-04-14', 'Lukas', 'Schnell', 'Kreuzlingen', '8512'),
	(22, '1997-01-05', 'Roman', 'Meier', 'Weinfelden', '5000'),
	(23, '1996-02-05', 'Reto', 'Mayer', 'Berg', '6000'),
	(24, '1997-03-11', 'Pascal', 'Radovic', 'Arbon', '8580'),
	(25, '1996-04-12', 'Yves', 'Gehring', 'Schaffhausen', '8454'),
	(26, '1997-08-12', 'Stefan', 'Witdmann', 'Tägerwilen', '8454'),
	(27, '1996-05-14', 'Lukas', 'Keller', 'Kreuzlingen', '8512');

		
-- --------------------------------------------------------
-- Table tournament_participant
-- --------------------------------------------------------	

INSERT INTO `tournament_participant` (`tournament_participant_id`, `group_id`, `participant_id`, `tournament_id`) VALUES
	(1, 1, 1, 1),
	(2, 1, 2, 1),
	(3, 1, 3, 1),
	(4, 1, 4, 1),
	(5, 1, 5, 1),
	(6, 2, 6, 1),
	(7, 2, 7, 1),
	(8, 2, 8, 1),
	(9, 2, 9, 1),
	(10, 2, 10, 1),
	(11, 3, 11, 2),
	(12, 3, 12, 2),
	(13, 3, 13, 2),
	(14, 3, 14, 2),
	(15, 4, 15, 2),
	(16, 4, 16, 2),
	(17, 4, 17, 2),
	(18, 4, 18, 2),
	(19, 4, 19, 2),
	(20, 5, 20, 3),
	(21, 5, 21, 3),
	(22, 6, 22, 3),
	(23, 6, 23, 3),
	(24, 7, 24, 4),
	(25, 7, 25, 4),
	(26, 8, 26, 4),
	(27, 8, 27, 4);

	
	
-- --------------------------------------------------------
-- Table tournamentgroup
-- --------------------------------------------------------	

INSERT INTO `tournament_group` (`group_id`, `group_name`,`tournament_id`) VALUES
	(1, 'ZHAW Turnier-1',1),
	(2, 'ZHAW Turnier-2',1),
	(3, 'Grümpi Sitterdorf-1',1),
	(4, 'Grümpi Sitterdorf-2',1),
	(5, 'Basketball-1',2),
	(6, 'Basketball-2',2),
	(7, 'Fussball-Plauschturnier Frauenfeld-1',2),
	(8, 'Fussball-Plauschturnier Frauenfeld-2',2);

	
-- --------------------------------------------------------
-- Table encounter
-- --------------------------------------------------------

INSERT INTO `encounter` (`encounter_id`, `group_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 2),
	(4, 2);
	

	
-- --------------------------------------------------------
-- Table game_set
-- --------------------------------------------------------


INSERT INTO `game_set` (`set_id`,`sequence_number`, `encounter_id`) VALUES
	(1,1,1),
	(2,2,1),
	(3,1,2),
	(4,2,2),
	(5,3,2),
	(6,1,3),
	(7,1,4);
	


INSERT INTO `encounter_tournament_participant` (`encounter_tournament_participant_id`, `encounter_id`, `tournament_participant_id`) VALUES
	(1,1,1),
	(2,1,2),
	(3,2,3),
	(4,2,4),
	(5,3,5),
	(6,3,6),
	(7,4,5),
	(8,4,8);

	
INSERT INTO `game_points` (`game_points_id`, `points`, `encounter_participant_id`, `game_set_id`) VALUES
	(1,1,1,1),
	(2,2,2,1),
	(3,10,1,2),
	(4,22,2,2),
	(5,5,3,3),
	(6,3,4,3),
	(7,9,3,4),
	(8,7,4,4),
	(9,0,3,5),
	(10,1,4,5),
	(11,4,5,6),
	(12,3,6,6),
	(13,6,7,7),
	(14,8,8,7);


REPLACE INTO `user` (`user_id`, `admin`, `password`, `username`) VALUES
	(1, b'1', '$2a$11$jmxtehhdqMQJJd/uBk3d4O1V1Jc9wj/2jRzYp6nGieQj6zfRN6ghC', 'admin@example.com');
-- Password == secret

INSERT INTO `tournament_user` (`tournament_user_id`, `role`, `tournament_id`, `user_id`) VALUES
	(1, 0, 1, 1),
	(2, 0, 2, 1),
	(3, 0, 3, 1),
	(4, 0, 4, 1),
	(5, 0, 5, 1),
	(6, 0, 6, 1),
	(7, 0, 7, 1),
	(8, 0, 8, 1),
	(9, 0, 9, 1),
	(10, 0, 10, 1),
	(11, 0, 11, 1),
	(12, 0, 12, 1),
	(13, 0, 13, 1);
