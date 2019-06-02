INSERT INTO public.profession("name", craft) VALUES('Blacksmith', true);
INSERT INTO public.profession("name", craft) VALUES('Miner', false);

INSERT INTO public.item ("name") VALUES('Iron chestplate');
INSERT INTO public.item ("name") VALUES('Iron ore');
INSERT INTO public.item ("name") VALUES('Iron bar');
INSERT INTO public.item ("name") VALUES('Leather');

INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(2, 3);
INSERT INTO public."recipe" (profession_id, created_item_id) VALUES(1, 1);

INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(1, 2, 2);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(2, 3, 10);
INSERT INTO public.recipe_item (recipe_id, required_item_id, count) VALUES(2, 4, 2);