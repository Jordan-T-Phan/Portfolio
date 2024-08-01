import java.util.ArrayList;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static final int NUM_OF_CITIES = 10;
    static final int POPULATION_SIZE = 500;
    static final int MAX_GENERATIONS = 100;
    static final double MUTATE_RATE = .2;
    static final int CROSSOVER_AMOUNT = (int) (.8 * POPULATION_SIZE);


    public static void main(String[] args) {

        City[] cities = new City[NUM_OF_CITIES];
        cities[0] = new City(0, 0);
        cities[1] = new City(1, 2);
        cities[2] = new City(3, 1);
        cities[3] = new City(4, 3);
        cities[4] = new City(2, 4);
        cities[5] = new City(0, 4);
        cities[6] = new City(5, 3);
        cities[7] = new City(1, 4);
        cities[8] = new City(8, 2);
        cities[9] = new City(5, 7);
        Route[] population = new Route[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population[i] = generateRandomRoute(cities);
        }
        for (int k = 0; k < MAX_GENERATIONS; k++) {
            for (int i = 0; i < POPULATION_SIZE; i++) {
               // System.out.println(Arrays.toString(population[i].getRoutes()));

            }
            int[][] parentsList = new int[CROSSOVER_AMOUNT][2];
            Route[][] childrensList = new Route[CROSSOVER_AMOUNT][2];
            for (int m = 0; m < CROSSOVER_AMOUNT; m++) {
             parentsList[m] =
                        selectParents(population);


                int[] parents = selectParents(population);
                ArrayList<Route> children = crossover(population[parents[0]], population[parents[1]]);
                childrensList[m][0] = children.get(0);
                childrensList[m][1] = children.get(1);




              //  int[] worstRoutes = findWorstRoutes(population);
             //   population[worstRoutes[0]] = children.get(0);
               // population[worstRoutes[1]] = children.get(1);
            }
            for(int m =0; m < childrensList.length; m ++){
                int[] worstRoutes = findWorstRoutes(population);
                population[worstRoutes[0]] = childrensList[m][0];
                population[worstRoutes[1]] = childrensList[m][1];
            }
            int popIndex = findBestRoute(population);
            System.out.println(popIndex + " " + population[popIndex].getFitness());
            System.out.println(Arrays.toString(population[popIndex].getRoutes()));
            System.out.println();
            System.out.println();
        }
    }

    public static int populationDistance(Route[] population) {
        double distance = 0;
        for (int i = 0; i < population.length; i++) {
            distance += population[i].getFitness();


        }
        return (int) distance;

    }


    public static Route generateRandomRoute(City[] cities) {
        ArrayList<Integer> cityPaths = new ArrayList<Integer>();
        for (int i = 0; i < NUM_OF_CITIES; i++) {
            cityPaths.add(i);

        }


        int[] routes = new int[NUM_OF_CITIES];
        int random;
        int random2;
        boolean go = false;
        for (int i = 0; i < routes.length; i++) {


            random = (int) (Math.random() * cityPaths.size());
            random2 = cityPaths.get(random);
            routes[i] = random2;
            cityPaths.remove(random);


        }
        return new Route(cities, routes);

    }

    public static ArrayList<Route> crossover(Route parent1, Route parent2) {
        ArrayList<Route> routes = new ArrayList<Route>();
        int random = (int) (Math.random() * (NUM_OF_CITIES - 1)) + 1;
        int[] offSpring1Route = new int[NUM_OF_CITIES];
        int[] offSpring2Route = new int[NUM_OF_CITIES];
        for (int i = 0; i < NUM_OF_CITIES; i++) {
            offSpring1Route[i] = -1;
            offSpring2Route[i] = -1;

        }
        for (int i = 0; i < random; i++) {
            offSpring1Route[i] = parent1.routes[i];
            offSpring2Route[i] = parent2.routes[i];

        }
        for (int i = random; i < NUM_OF_CITIES; i++) {
            int city1 = parent2.routes[i];
            int city2 = parent2.routes[i];
            boolean firstTrue = true;
            boolean secondTrue = true;
            for (int j = 0; j < NUM_OF_CITIES; j++) {
                if (city1 == offSpring1Route[j]) {
                    firstTrue = false;
                    break;
                }

            }
            if (firstTrue) {
                offSpring1Route[i] = city1;
            }
            for (int j = 0; j < NUM_OF_CITIES; j++) {
                if (city2 == offSpring2Route[j]) {
                    secondTrue = false;
                    break;
                }

            }
            if (secondTrue) {
                offSpring2Route[i] = city2;
            }
        }
        for (int i = 0; i < NUM_OF_CITIES; i++) {
            boolean cityExist = false;
            for (int j = 0; j < NUM_OF_CITIES; j++) {
                if (i == offSpring1Route[j]) {
                    cityExist = true;
                    break;
                }


            }
            if (!cityExist) {
                for (int j = 0; j < NUM_OF_CITIES; j++) {
                    if (offSpring1Route[j] == -1) {
                        offSpring1Route[j] = i;
                        break;
                    }


                }
            }
        }
        for (int i = 0; i < NUM_OF_CITIES; i++) {
            boolean cityExist = false;
            for (int j = 0; j < NUM_OF_CITIES; j++) {
                if (i == offSpring2Route[j]) {
                    cityExist = true;
                    break;
                }


            }
            if (!cityExist) {
                for (int j = 0; j < NUM_OF_CITIES; j++) {
                    if (offSpring2Route[j] == -1) {
                        offSpring2Route[j] = i;
                        break;
                    }


                }
            }
        }
        Route route1 = (new Route(parent1.cities, offSpring1Route));
        Route route2 = new Route(parent1.cities, offSpring2Route);
        route1.mutate(MUTATE_RATE);
        route2.mutate(MUTATE_RATE);

        routes.add(route1);
        routes.add(route2);
        return routes;


    }

    public static int findBestRoute(Route[] population) {
        double min = population[0].getFitness();
        int index = 0;
        for (int i = 1; i < population.length; i++) {
            if (min - population[i].getFitness() > 0.001) {
                min = population[i].getFitness();
                index = i;
            }

        }
        return index;


    }

    public static int[] findWorstRoutes(Route[] Population) {
        int max1 = (int) Population[0].getFitness();
        int index1 = 0;
        int max2 = (int) Population[1].getFitness();
        int index2 = 1;
        int[] array = new int[2];

        for (int i = 2; i < Population.length; i++) {
            if ((int) Population[i].getFitness() > max1 || (int) Population[i].getFitness() > max2) {
                if (max1 < max2) {
                    index1 = i;
                    max1 = (int) Population[i].getFitness();
                } else {
                    index2 = i;
                    max2 = (int) Population[i].getFitness();
                }


            }


        }
        array[0] = index1;
        array[1] = index2;
        return array;


    }

    public static int[] selectParents(Route[] population) {

        int populationDistance = populationDistance(population);
       // System.out.println(populationDistance);
        int randomNum = (int) (Math.random() * populationDistance);
        double selectedDistance = 0;
        int index1 = -1;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            selectedDistance += population[i].getFitness();
            if (randomNum <= selectedDistance) {
                index1 = i;
                break;
            }
        }
        randomNum = (int) (Math.random() * populationDistance);
        int index2 = -1;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            selectedDistance += population[i].getFitness();
            if (randomNum <= selectedDistance) {
                index2 = i;
                break;
            }
        }
        int[] array = {index1, index2};
        return array;

    }


}

