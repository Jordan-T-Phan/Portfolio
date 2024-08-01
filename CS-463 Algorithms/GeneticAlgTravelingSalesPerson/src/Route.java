import java.util.Arrays;

public class Route {
    City[] cities;
    int[] routes;
    double fitness;

    public Route(City[] cities, int[] routes) {
        this.cities = cities;
        this.routes = routes;
        this.fitness = calculateFitness();
    }

    public double calculateFitness() {
        double fit = 0;
        for (int i = 0; i < cities.length - 1; i++) {
            fit += City.Distance(cities[routes[i]], cities[routes[i + 1]]);

        }
        fit += City.Distance(cities[routes[routes.length - 1]], cities[routes[0]]);
        return fit;
    }

    public void mutate(double mutateRate) {
//System.out.println("original");
//System.out.println(Arrays.toString(routes));
        double mutate_Prob = (int) Math.random();
        if (mutateRate - mutate_Prob <= .03) {

            int city1 = (int) (Math.random() * routes.length);
            int city2 = (int) (Math.random() * routes.length);
            int temp = routes[city1];
            routes[city1] = routes[city2];
            routes[city2] = temp;
//System.out.println("mutate");
            //System.out.println(Arrays.toString(routes));

        }
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int[] getRoutes() {
        return routes;
    }

    public void setRoutes(int[] routes) {
        this.routes = routes;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }
}
