INSERT INTO PARKING (
    commune,
    url_list_parking,
    url_dispo_parking,
    url_list_parking_select1,
    url_list_parking_select2,
    url_list_parking_select3,
    url_dispo_parking_select1,
    url_dispo_parking_select2,
    url_dispo_parking_select3
) VALUES
      ('grandpoitiers', 'https://data.grandpoitiers.fr/data-fair/api/v1/datasets/mobilite-parkings-grand-poitiers-donnees-metiers/lines/', 'https://data.grandpoitiers.fr/data-fair/api/v1/datasets/mobilites-stationnement-des-parkings-en-temps-reel/lines', 'nom', 'geo_point', '', 'Nom', 'Places', 'Capacite'),
      ('paris', 'https://opendata.paris.fr/api/explore/v2.1/catalog/datasets/stationnement-en-ouvrage/records', '', 'nom', '', '', '', '', ''),
      ('orleans', 'https://data.orleans-metropole.fr/api/explore/v2.1/catalog/datasets/mobilite-places-disponibles-parkings-en-temps-reel/records', '', 'name', '', '', '', '', ''),
      ('blabla', 'https://data.grandpoitiers.fr/data-fair/api/v1/datasets/mobilite-parkings-grand-poitiers-donnees-metiers/lines/', '', 'nom', '', '', '', '', '');
