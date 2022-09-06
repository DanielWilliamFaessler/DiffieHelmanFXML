class randomGenerator{
    public static void main( String args[] ) {
        int min = 1;
        int max = 1000;

        //Generate Value between min and max


        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            System.out.println(random_int);

    }
}