-- 30-car dataset with fixed, model-specific Wikimedia Commons image URLs.
-- These are static file URLs, not keyword-generated/random image services.
-- Prices/specifications are approximate demo data and should be refreshed for production.
-- Wikimedia-hosted images may require attribution depending on each file's license.

INSERT INTO car (
    make,
    model,
    variant,
    price_in_lakhs,
    body_type,
    fuel_type,
    transmission,
    mileage,
    seating_capacity,
    safety_rating,
    segment,
    image_url
)
VALUES
('Maruti Suzuki', 'Dzire', 'ZXI Plus', 10.19, 'Sedan', 'Petrol', 'Automatic', 25.71, 5, 5.0, 'Compact Sedan', 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Suzuki_Dzire_II_1.2_GLX_Hybrid_Arctic_White_Pearl.jpg/900px-Suzuki_Dzire_II_1.2_GLX_Hybrid_Arctic_White_Pearl.jpg'),
('Tata', 'Punch', 'Creative Plus', 9.45, 'SUV', 'Petrol', 'Automatic', 18.80, 5, 5.0, 'Micro SUV', 'https://upload.wikimedia.org/wikipedia/commons/1/1e/2021_Tata_Punch_Creative_%28India%29_front_view_01.png'),
('Hyundai', 'Creta', 'SX', 15.30, 'SUV', 'Petrol', 'Automatic', 17.40, 5, 3.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/2/25/2022_Hyundai_Creta_1.6_Plus_%28Chile%29_front_view.jpg'),
('Tata', 'Nexon', 'Creative Plus', 12.30, 'SUV', 'Petrol', 'Automatic', 17.00, 5, 5.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Tata_Nexon_Blue_Dual_Tone.jpg/900px-Tata_Nexon_Blue_Dual_Tone.jpg'),
('Maruti Suzuki', 'Fronx', 'Alpha Turbo', 11.98, 'SUV', 'Petrol', 'Automatic', 20.01, 5, 4.0, 'Crossover SUV', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/2024_Suzuki_Fronx.jpg/900px-2024_Suzuki_Fronx.jpg'),
('Maruti Suzuki', 'Swift', 'ZXI Plus', 8.84, 'Hatchback', 'Petrol', 'Automatic', 25.75, 5, 3.0, 'Premium Hatchback', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Suzuki_Swift_%282024%29_hybrid_DSC_6076.jpg/900px-Suzuki_Swift_%282024%29_hybrid_DSC_6076.jpg'),
('Maruti Suzuki', 'Baleno', 'Alpha', 9.92, 'Hatchback', 'Petrol', 'Automatic', 22.94, 5, 3.0, 'Premium Hatchback', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/2017_Suzuki_Baleno_SZ3_Dualjet_1.2_Front.jpg/900px-2017_Suzuki_Baleno_SZ3_Dualjet_1.2_Front.jpg'),
('Maruti Suzuki', 'Brezza', 'ZXI Plus', 14.14, 'SUV', 'Petrol', 'Automatic', 19.80, 5, 4.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/e/ee/2022_Maruti_Suzuki_Brezza_ZXi%2B_%28India%29_front_view_03.png'),
('Mahindra', 'XUV700', 'AX7 Luxury', 25.89, 'SUV', 'Diesel', 'Automatic', 16.57, 7, 5.0, 'Mid-size SUV', 'https://upload.wikimedia.org/wikipedia/commons/b/ba/2021_Mahindra_XUV700_2.2_AX7_%28India%29_front_view.png'),
('Mahindra', 'Thar', 'LX Hard Top', 17.60, 'SUV', 'Diesel', 'Automatic', 15.20, 4, 4.0, 'Off-road SUV', 'https://upload.wikimedia.org/wikipedia/commons/5/51/Mahindra_Thar_SUV_in_%22Red_Rage%22_color_at_Ashiana_Brahmanda%2C_East_Singbhum_India_%28Ank_Kumar%2C_Infosys_limited%29_02_%28cropped%29.jpg'),
('Hyundai', 'Venue', 'SX(O)', 13.48, 'SUV', 'Petrol', 'Automatic', 18.31, 5, 4.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/0/0c/2022_Hyundai_Venue_Preferred_in_Polar_White%2C_Front_Right%2C_09-12-2023.jpg'),
('Kia', 'Sonet', 'GTX Plus', 15.77, 'SUV', 'Diesel', 'Automatic', 19.00, 5, 3.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/7/75/2021_Kia_Sonet_1.5_Premiere_%28Indonesia%29_front_view_02.jpg'),
('Kia', 'Seltos', 'HTX', 16.90, 'SUV', 'Petrol', 'Automatic', 17.70, 5, 3.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/6/6c/Kia_Seltos_SP2_PE_Snow_White_Pearl_%2817%29_%28cropped%29.jpg'),
('Toyota', 'Fortuner', 'Legender 4x4', 51.94, 'SUV', 'Diesel', 'Automatic', 14.20, 7, 5.0, 'Full-size SUV', 'https://upload.wikimedia.org/wikipedia/commons/6/66/2015_Toyota_Fortuner_%28New_Zealand%29.jpg'),
('Toyota', 'Innova Hycross', 'ZX(O)', 30.98, 'MPV', 'Hybrid', 'Automatic', 23.24, 7, 5.0, 'Premium MPV', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/2022_Toyota_Kijang_Innova_2.4_G_GUN142R_%2820220302%29.jpg/900px-2022_Toyota_Kijang_Innova_2.4_G_GUN142R_%2820220302%29.jpg'),
('Honda', 'City', 'ZX', 16.55, 'Sedan', 'Petrol', 'Automatic', 18.40, 5, 5.0, 'Mid-size Sedan', 'https://upload.wikimedia.org/wikipedia/commons/a/a9/2022_Honda_City_ZX_i-VTEC_%28India%29_front_view_%28cropped%29.jpg'),
('Skoda', 'Slavia', 'Prestige', 18.69, 'Sedan', 'Petrol', 'Automatic', 19.36, 5, 5.0, 'Mid-size Sedan', 'https://upload.wikimedia.org/wikipedia/commons/9/92/2021_%C5%A0koda_Slavia_1.5_TSI_Style_%28India%29_front_view.png'),
('Volkswagen', 'Virtus', 'GT Plus Sport', 19.40, 'Sedan', 'Petrol', 'Automatic', 19.62, 5, 5.0, 'Mid-size Sedan', 'https://upload.wikimedia.org/wikipedia/commons/9/9e/2022_Volkswagen_Virtus_1.5_GT_%28India%29_front_view_02.png'),
('Tata', 'Harrier', 'Fearless Plus', 26.50, 'SUV', 'Diesel', 'Automatic', 16.80, 5, 5.0, 'Mid-size SUV', 'https://upload.wikimedia.org/wikipedia/commons/c/c3/Tata_Buzzard_Sport%2C_GIMS_2019%2C_Le_Grand-Saconnex_%28GIMS0651%29.jpg'),
('Renault', 'Kwid', 'Climber', 6.45, 'Hatchback', 'Petrol', 'Automatic', 22.30, 5, 1.0, 'Entry Hatchback', 'https://upload.wikimedia.org/wikipedia/commons/7/77/2023_Renault_Kwid_Iconic_%28Colombia%29_front_view_01.png'),
('Renault', 'Kiger', 'Emotion Turbo', 11.29, 'SUV', 'Petrol', 'Automatic', 18.20, 5, 4.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Renault_Kiger_front_20230602.jpg/900px-Renault_Kiger_front_20230602.jpg'),
('Nissan', 'Magnite', 'Tekna Plus', 11.76, 'SUV', 'Petrol', 'Automatic', 17.90, 5, 4.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Nissan_Magnite_2025_1.0_Exclusive_at_WTC_Montevideo_-_04.jpg/900px-Nissan_Magnite_2025_1.0_Exclusive_at_WTC_Montevideo_-_04.jpg'),
('Jeep', 'Compass', 'Model S', 32.41, 'SUV', 'Diesel', 'Automatic', 17.10, 5, 5.0, 'Premium SUV', 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/2019_Jeep_Compass_Limited_2.4L%2C_front_7.6.19.jpg/900px-2019_Jeep_Compass_Limited_2.4L%2C_front_7.6.19.jpg'),
('BYD', 'Atto 3', 'Superior', 33.99, 'SUV', 'Electric', 'Automatic', 521.00, 5, 5.0, 'Electric SUV', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/BYD_Atto_3_1X7A6491.jpg/900px-BYD_Atto_3_1X7A6491.jpg'),
('Tata', 'Tiago', 'XZ Plus', 8.20, 'Hatchback', 'Petrol', 'Automatic', 19.00, 5, 4.0, 'Hatchback', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/2022_Tata_Tiago_XZA%2B_front_20230512.jpg/900px-2022_Tata_Tiago_XZA%2B_front_20230512.jpg'),
('Hyundai', 'i20', 'Asta(O)', 11.25, 'Hatchback', 'Petrol', 'Automatic', 20.00, 5, 3.0, 'Premium Hatchback', 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Hyundai_i20_%28III%2C_Facelift%29_%E2%80%93_f_11102025.jpg/900px-Hyundai_i20_%28III%2C_Facelift%29_%E2%80%93_f_11102025.jpg'),
('Maruti Suzuki', 'Grand Vitara', 'Alpha Plus Hybrid', 20.68, 'SUV', 'Hybrid', 'Automatic', 27.97, 5, 4.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/0/0f/2022_Suzuki_Grand_Vitara_GX_Smart_Hybrid_%28Indonesia%29_front_view.jpg'),
('Skoda', 'Kushaq', 'Prestige', 20.49, 'SUV', 'Petrol', 'Automatic', 18.86, 5, 5.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/2/29/2021_%C5%A0koda_Kushaq_%28India%29_front_view.png'),
('Volkswagen', 'Taigun', 'GT Plus Sport', 20.00, 'SUV', 'Petrol', 'Automatic', 18.61, 5, 5.0, 'Compact SUV', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Volkswagen_Taigun_front_20230528.jpg/900px-Volkswagen_Taigun_front_20230528.jpg'),
('Honda', 'Amaze', 'ZX', 11.20, 'Sedan', 'Petrol', 'Automatic', 19.46, 5, 5.0, 'Compact Sedan', 'https://upload.wikimedia.org/wikipedia/commons/2/25/Honda_Amaze_front_view_%28cropped%29.jpg');
