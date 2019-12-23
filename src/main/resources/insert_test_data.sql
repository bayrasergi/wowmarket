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
INSERT INTO public.certificate("profession_id", user_id, approved, link) VALUES(1,6, true, 'www.example.com');
INSERT INTO public.certificate("profession_id", user_id, approved, link) VALUES(2,7, true, 'www.example.com');
INSERT INTO public.certificate("profession_id", user_id, approved, link) VALUES(3,8, true, 'www.example.com');
INSERT INTO public.certificate("profession_id", user_id, approved, link) VALUES(4,9, true, 'www.example.com');
INSERT INTO public.certificate("profession_id", user_id, approved, link) VALUES(5,10, true, 'www.example.com');
INSERT INTO public.certificate("profession_id", user_id, approved, link) VALUES(6,11, true, 'www.example.com');
INSERT INTO public.certificate("profession_id", user_id, approved, link) VALUES(7,12, true, 'www.example.com');
INSERT INTO public.certificate("profession_id", user_id, approved, link) VALUES(1,13, false, 'www.example.com');

-- Tailoring
INSERT INTO public.item ("name","type") VALUES('Linen Cloth','STANDARD'); -- 1
INSERT INTO public.item ("name","type") VALUES('Bolt of Linen Cloth','CRAFTABLE'); -- 2
INSERT INTO public.item ("name","type") VALUES('Coarse Thread','STANDARD'); -- 3
INSERT INTO public.item ("name","type") VALUES('Red Dye','STANDARD'); -- 4
INSERT INTO public.item ("name","type") VALUES('Gray Dye','STANDARD'); -- 5
INSERT INTO public.item ("name","type") VALUES('Wool Cloth','STANDARD'); -- 6
INSERT INTO public.item ("name","type") VALUES('Bolt of Woolen Cloth','CRAFTABLE'); -- 7
INSERT INTO public.item ("name","type") VALUES('Fine Thread','STANDARD'); -- 8

INSERT INTO public.item ("name","type") VALUES('Heavy Linen Gloves','CRAFTABLE'); -- 9
INSERT INTO public.item ("name","type") VALUES('Red Linen Shirt','CRAFTABLE'); -- 10
INSERT INTO public.item ("name","type") VALUES('Reinforced Linen Cape','CRAFTABLE'); -- 11
INSERT INTO public.item ("name","type") VALUES('Gray Woolen Shirt','CRAFTABLE'); -- 12
INSERT INTO public.item ("name","type") VALUES('Double-stitched Woolen Shoulders','CRAFTABLE'); -- 13

-- Herbalism Alchemy
INSERT INTO public.item ("name","type") VALUES('Peacebloom','COLLECTIBLE'); -- 14
INSERT INTO public.item ("name","type") VALUES('Silverleaf','COLLECTIBLE'); -- 15
INSERT INTO public.item ("name","type") VALUES('Earthroot','COLLECTIBLE'); -- 16
INSERT INTO public.item ("name","type") VALUES('Empty Vial','STANDARD'); -- 17

INSERT INTO public.item ("name","type") VALUES('Minor Healing Potion','CRAFTABLE'); -- 18
INSERT INTO public.item ("name","type") VALUES('Weak Troll''s Blood Potion','CRAFTABLE'); -- 19
INSERT INTO public.item ("name","type") VALUES('Elixir of Minor Fortitude','CRAFTABLE'); -- 20

-- Mining Blacksmithing
INSERT INTO public.item ("name","type") VALUES('Rough Stone','COLLECTIBLE'); -- 21
INSERT INTO public.item ("name","type") VALUES('Malachite','COLLECTIBLE'); -- 22
INSERT INTO public.item ("name","type") VALUES('Copper ore','COLLECTIBLE'); -- 23
INSERT INTO public.item ("name","type") VALUES('Copper bar','COLLECTIBLE'); -- 24
INSERT INTO public.item ("name","type") VALUES('Weak Flux','STANDARD'); -- 25

INSERT INTO public.item ("name","type") VALUES('Rough Grinding Stone','CRAFTABLE'); -- 26
INSERT INTO public.item ("name","type") VALUES('Copper Axe','CRAFTABLE'); -- 27
INSERT INTO public.item ("name","type") VALUES('Copper Mace','CRAFTABLE'); -- 28
INSERT INTO public.item ("name","type") VALUES('Copper Bracers','CRAFTABLE'); -- 29
INSERT INTO public.item ("name","type") VALUES('Copper Chain Vest','CRAFTABLE'); -- 30
INSERT INTO public.item ("name","type") VALUES('Copper Dagger','CRAFTABLE'); -- 31
INSERT INTO public.item ("name","type") VALUES('Copper Battle Axe','CRAFTABLE'); -- 32

-- Skinning Leatherworking
INSERT INTO public.item ("name","type") VALUES('Ruined Leather Scraps','COLLECTIBLE'); -- 33
INSERT INTO public.item ("name","type") VALUES('Light Leather','COLLECTIBLE'); -- 34
INSERT INTO public.item ("name","type") VALUES('Light Hide','COLLECTIBLE'); -- 35
INSERT INTO public.item ("name","type") VALUES('Salt','STANDARD'); -- 36

INSERT INTO public.item ("name","type") VALUES('Cured Light Hide','CRAFTABLE'); -- 37
INSERT INTO public.item ("name","type") VALUES('Fine Leather Belt','CRAFTABLE'); -- 38
INSERT INTO public.item ("name","type") VALUES('Fine Leather Boots','CRAFTABLE'); -- 39
INSERT INTO public.item ("name","type") VALUES('Fine Leather Gloves','CRAFTABLE'); -- 40
INSERT INTO public.item ("name","type") VALUES('Embossed Leather Pants','CRAFTABLE'); -- 41

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

-- Food
INSERT INTO public.item ("name","type") VALUES('Ice Cold Milk','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Alterac Swiss','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Refreshed Spring Water','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Smoked Desert Dumplings','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Grilled Squid','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Roasted Quail','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Candy Cane','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Fine Aged Cheddar','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Tender Wolf Steak','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Cured Ham Steak','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Southshore Stout','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Shiny Red Apple','STANDARD');

-- Rings
INSERT INTO public.item ("name","type") VALUES('Mood Ring','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Ring of Scorn','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Quartz Ring','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Meadow Ring','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Clay Ring','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Blood Ring','STANDARD');

-- Amulet
INSERT INTO public.item ("name","type") VALUES('Glowing Green Talisman','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Crystal Starfire Medallion','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Basalt Necklace','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Necklace of Harmony','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Greenstone Talisman','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Marsh Chain','STANDARD');

-- Weapon
INSERT INTO public.item ("name","type") VALUES('Keen Machete','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Fine Scimitar','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Dull Blade','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Gladius','STANDARD');

INSERT INTO public.item ("name","type") VALUES('Ashwood Bow','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Polished Shortbow','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Hunting Bow','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Heavy Shortbow','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Light Crossbow','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Heavy Crossbow','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Blackcrow','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Steelarrow Crossbow','STANDARD');

INSERT INTO public.item ("name","type") VALUES('Worn Battleaxe','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Large Axe','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Battleworn Hammer','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Short Staff','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Bent Staff','STANDARD');

-- Armor
INSERT INTO public.item ("name","type") VALUES('Tarnished Chain Vest','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Rusted Chain Leggings','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Tarnished Chain Belt','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Charger''s Bindings','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Bloody Chain Boots','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Warrior''s Gloves','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Infantry Boots','STANDARD');
INSERT INTO public.item ("name","type") VALUES('Infantry Leggings','STANDARD');

-- Lots
INSERT INTO public."lot" (item_id, seller_user_id, "count", price, status) VALUES(40, 2, 12, 24, 'SELLING');
INSERT INTO public."lot" (item_id, seller_user_id, "count", price, status) VALUES(41, 2, 4, 120, 'SELLING');
INSERT INTO public."lot" (item_id, seller_user_id, "count", price, status) VALUES(50, 2, 1, 60, 'SOLD');
INSERT INTO public."lot" (item_id, seller_user_id, "count", price, status) VALUES(51, 2, 4, 40, 'SOLD');