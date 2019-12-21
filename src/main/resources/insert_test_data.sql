-- Users
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('admin', 'Admin','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'ADMIN', false); -- 1
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('user1', 'User 1','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'USER', false); -- 2
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('user2', 'User 2','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'USER', false); -- 3
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('locked', 'locked','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'USER', true); -- 4
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('creator', 'Creator','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'USER', false); -- 5
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('mining', 'mining','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'COLLECTOR', false); -- 6
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('herbalist', 'herbalist','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'COLLECTOR', false); -- 7
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('skinner', 'skinner','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'COLLECTOR', false); -- 8
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('tailor', 'tailor','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'CREATOR', false); -- 9
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('alchemist', 'alchemist','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'CREATOR', false); -- 10
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('blacksmither', 'blacksmither','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'CREATOR', false); -- 11
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('leatherworker', 'leatherworker','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'CREATOR', false); -- 12
INSERT INTO public."user" (username,"name",password,role, locked) VALUES('approve', 'approve','$2a$10$/61JctZTPpbM6SDyWmcwhe0lyvWI/VK7E.CrCPf7/EiZv2g3LsSEy', 'USER', false); -- 13

-- Professions
INSERT INTO public.profession("name", craft) VALUES('Mining', false); -- 1
INSERT INTO public.profession("name", craft) VALUES('Herbalism', false); -- 2
INSERT INTO public.profession("name", craft) VALUES('Skinning', false); -- 3
INSERT INTO public.profession("name", craft) VALUES('Tailoring', true); -- 4
INSERT INTO public.profession("name", craft) VALUES('Alchemy', true); -- 5
INSERT INTO public.profession("name", craft) VALUES('Blacksmithing', true); -- 6
INSERT INTO public.profession("name", craft) VALUES('Leatherworking', true); -- 7

-- Certificates
INSERT INTO public.certificate("profession_id", user_id, approved) VALUES(1,6, true);
INSERT INTO public.certificate("profession_id", user_id, approved) VALUES(2,7, true);
INSERT INTO public.certificate("profession_id", user_id, approved) VALUES(3,8, true);
INSERT INTO public.certificate("profession_id", user_id, approved) VALUES(4,9, true);
INSERT INTO public.certificate("profession_id", user_id, approved) VALUES(5,10, true);
INSERT INTO public.certificate("profession_id", user_id, approved) VALUES(6,11, true);
INSERT INTO public.certificate("profession_id", user_id, approved) VALUES(7,12, true);
INSERT INTO public.certificate("profession_id", user_id, approved) VALUES(1,13, false);

-- Tailoring
INSERT INTO public.item ("name") VALUES('Linen Cloth'); -- 1
INSERT INTO public.item ("name") VALUES('Bolt of Linen Cloth'); -- 2
INSERT INTO public.item ("name") VALUES('Coarse Thread'); -- 3
INSERT INTO public.item ("name") VALUES('Red Dye'); -- 4
INSERT INTO public.item ("name") VALUES('Gray Dye'); -- 5
INSERT INTO public.item ("name") VALUES('Wool Cloth'); -- 6
INSERT INTO public.item ("name") VALUES('Bolt of Woolen Cloth'); -- 7
INSERT INTO public.item ("name") VALUES('Fine Thread'); -- 8

INSERT INTO public.item ("name") VALUES('Heavy Linen Gloves'); -- 9
INSERT INTO public.item ("name") VALUES('Red Linen Shirt'); -- 10
INSERT INTO public.item ("name") VALUES('Reinforced Linen Cape'); -- 11
INSERT INTO public.item ("name") VALUES('Gray Woolen Shirt'); -- 12
INSERT INTO public.item ("name") VALUES('Double-stitched Woolen Shoulders'); -- 13

-- Herbalism Alchemy
INSERT INTO public.item ("name") VALUES('Peacebloom'); -- 14
INSERT INTO public.item ("name") VALUES('Silverleaf'); -- 15
INSERT INTO public.item ("name") VALUES('Earthroot'); -- 16
INSERT INTO public.item ("name") VALUES('Empty Vial'); -- 17

INSERT INTO public.item ("name") VALUES('Minor Healing Potion'); -- 18
INSERT INTO public.item ("name") VALUES('Weak Troll''s Blood Potion'); -- 19
INSERT INTO public.item ("name") VALUES('Elixir of Minor Fortitude'); -- 20

-- Mining Blacksmithing
INSERT INTO public.item ("name") VALUES('Rough Stone'); -- 21
INSERT INTO public.item ("name") VALUES('Malachite'); -- 22
INSERT INTO public.item ("name") VALUES('Copper ore'); -- 23
INSERT INTO public.item ("name") VALUES('Copper bar'); -- 24
INSERT INTO public.item ("name") VALUES('Weak Flux'); -- 25

INSERT INTO public.item ("name") VALUES('Rough Grinding Stone'); -- 26
INSERT INTO public.item ("name") VALUES('Copper Axe'); -- 27
INSERT INTO public.item ("name") VALUES('Copper Mace'); -- 28
INSERT INTO public.item ("name") VALUES('Copper Bracers'); -- 29
INSERT INTO public.item ("name") VALUES('Copper Chain Vest'); -- 30
INSERT INTO public.item ("name") VALUES('Copper Dagger'); -- 31
INSERT INTO public.item ("name") VALUES('Copper Battle Axe'); -- 32

-- Skinning Leatherworking
INSERT INTO public.item ("name") VALUES('Ruined Leather Scraps'); -- 33
INSERT INTO public.item ("name") VALUES('Light Leather'); -- 34
INSERT INTO public.item ("name") VALUES('Light Hide'); -- 35
INSERT INTO public.item ("name") VALUES('Salt'); -- 36

INSERT INTO public.item ("name") VALUES('Cured Light Hide'); -- 37
INSERT INTO public.item ("name") VALUES('Fine Leather Belt'); -- 38
INSERT INTO public.item ("name") VALUES('Fine Leather Boots'); -- 39
INSERT INTO public.item ("name") VALUES('Fine Leather Gloves'); -- 40
INSERT INTO public.item ("name") VALUES('Embossed Leather Pants'); -- 41

INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(1, 21); -- 1
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(1, 23); -- 2
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(1, 22); -- 3

INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(2, 14); -- 4
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(2, 15); -- 5
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(2, 16); -- 6

INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(3, 33); -- 7
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(3, 35); -- 8

INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(4, 2); -- 9
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(4, 7); -- 10
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(4, 9); -- 11
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(4, 10); -- 12
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(4, 11); -- 13
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(4, 12); -- 14
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(4, 13); -- 15

INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(5, 18); -- 16
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(5, 19); -- 17
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(5, 20); -- 18

INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(6, 24); -- 19
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(6, 26); -- 20
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(6, 27); -- 21
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(6, 28); -- 22
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(6, 29); -- 23
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(6, 30); -- 24
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(6, 31); -- 25
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(6, 32); -- 26

INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(7, 34); -- 27
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(7, 37); -- 28
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(7, 38); -- 29
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(7, 39); -- 31
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(7, 40); -- 32
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(7, 41); -- 33

-- Recipe Items
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(9, 1, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(10, 6, 3);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(11, 2, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(11, 3, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(12, 2, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(12, 3, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(12, 4, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(13, 2, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(13, 3, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(14, 7, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(14, 8, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(14, 5, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(15, 7, 3);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(15, 8, 2);

INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(16, 14, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(16, 15, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(16, 17, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(17, 14, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(17, 16, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(17, 17, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(18, 17, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(18, 16, 2);

INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(19, 23, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(20, 21, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(21, 24, 6);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(21, 25, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(21, 6, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(22, 24, 6);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(22, 25, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(22, 6, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(23, 24, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(24, 24, 8);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(24, 22, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(24, 26, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(25, 24, 6);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(25, 25, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(25, 26, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(25, 34, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(26, 24, 12);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(26, 34, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(26, 22, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(26, 25, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(26, 26, 2);

INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(27, 33, 3);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(28, 35, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(28, 36, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(29, 34, 6);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(29, 3, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(30, 34, 6);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(30, 3, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(31, 34, 4);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(31, 3, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(31, 37, 1);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(32, 34, 6);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(32, 3, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(32, 37, 1);

-- Lots
INSERT INTO public."lot" (item_id, seller_user_id, "count", price, status) VALUES(40, 2, 12, 24, 'AVAILABLE');
INSERT INTO public."lot" (item_id, seller_user_id, "count", price, status) VALUES(41, 2, 4, 120, 'AVAILABLE');
INSERT INTO public."lot" (item_id, seller_user_id, "count", price, status) VALUES(50, 2, 1, 60, 'SELL');
INSERT INTO public."lot" (item_id, seller_user_id, "count", price, status) VALUES(51, 2, 4, 40, 'SELL');

-- Food
INSERT INTO public.item ("name") VALUES('Ice Cold Milk');
INSERT INTO public.item ("name") VALUES('Alterac Swiss');
INSERT INTO public.item ("name") VALUES('Refreshed Spring Water');
INSERT INTO public.item ("name") VALUES('Smoked Desert Dumplings');
INSERT INTO public.item ("name") VALUES('Grilled Squid');
INSERT INTO public.item ("name") VALUES('Roasted Quail');
INSERT INTO public.item ("name") VALUES('Candy Cane');
INSERT INTO public.item ("name") VALUES('Fine Aged Cheddar');
INSERT INTO public.item ("name") VALUES('Tender Wolf Steak');
INSERT INTO public.item ("name") VALUES('Cured Ham Steak');
INSERT INTO public.item ("name") VALUES('Southshore Stout');
INSERT INTO public.item ("name") VALUES('Shiny Red Apple');

-- Rings
INSERT INTO public.item ("name") VALUES('Mood Ring');
INSERT INTO public.item ("name") VALUES('Ring of Scorn');
INSERT INTO public.item ("name") VALUES('Quartz Ring');
INSERT INTO public.item ("name") VALUES('Meadow Ring');
INSERT INTO public.item ("name") VALUES('Clay Ring');
INSERT INTO public.item ("name") VALUES('Blood Ring');

-- Amulet
INSERT INTO public.item ("name") VALUES('Glowing Green Talisman');
INSERT INTO public.item ("name") VALUES('Crystal Starfire Medallion');
INSERT INTO public.item ("name") VALUES('Basalt Necklace');
INSERT INTO public.item ("name") VALUES('Necklace of Harmony');
INSERT INTO public.item ("name") VALUES('Greenstone Talisman');
INSERT INTO public.item ("name") VALUES('Marsh Chain');

-- Weapon
INSERT INTO public.item ("name") VALUES('Keen Machete');
INSERT INTO public.item ("name") VALUES('Fine Scimitar');
INSERT INTO public.item ("name") VALUES('Dull Blade');
INSERT INTO public.item ("name") VALUES('Gladius');

INSERT INTO public.item ("name") VALUES('Ashwood Bow');
INSERT INTO public.item ("name") VALUES('Polished Shortbow');
INSERT INTO public.item ("name") VALUES('Hunting Bow');
INSERT INTO public.item ("name") VALUES('Heavy Shortbow');
INSERT INTO public.item ("name") VALUES('Light Crossbow');
INSERT INTO public.item ("name") VALUES('Heavy Crossbow');
INSERT INTO public.item ("name") VALUES('Blackcrow');
INSERT INTO public.item ("name") VALUES('Steelarrow Crossbow');

INSERT INTO public.item ("name") VALUES('Worn Battleaxe');
INSERT INTO public.item ("name") VALUES('Large Axe');
INSERT INTO public.item ("name") VALUES('Battleworn Hammer');
INSERT INTO public.item ("name") VALUES('Short Staff');
INSERT INTO public.item ("name") VALUES('Bent Staff');

-- Armor
INSERT INTO public.item ("name") VALUES('Tarnished Chain Vest');
INSERT INTO public.item ("name") VALUES('Rusted Chain Leggings');
INSERT INTO public.item ("name") VALUES('Tarnished Chain Belt');
INSERT INTO public.item ("name") VALUES('Charger''s Bindings');
INSERT INTO public.item ("name") VALUES('Bloody Chain Boots');
INSERT INTO public.item ("name") VALUES('Warrior''s Gloves');
INSERT INTO public.item ("name") VALUES('Infantry Boots');
INSERT INTO public.item ("name") VALUES('Infantry Leggings');
