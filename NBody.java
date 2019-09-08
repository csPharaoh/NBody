public class NBody 
{
    public static void main(String[] args)
    {



        StdDraw.enableDoubleBuffering();
        double [] xForces;
        double [] yForces;                           
        double time=0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body [] P=readBodies(filename);
        double radius = readRadius(filename);
        
        
        int i=0;
        int counter =0;
        
        StdDraw.setScale(-1* radius, radius);
        



        while (time < T)
        {
            
          xForces = new double [P.length];
          yForces = new double [P.length];

            for (Body element : P) 
            {
              xForces [counter] = element.calcNetForceExertedByX(P);
              yForces[counter] = element.calcNetForceExertedByY(P);              
              counter = counter +1;             
            }


            counter = 0;

            for (Body element : P) 
            {
              element.update(dt, xForces[i], yForces[i]);
              i = i+1;
            }

            StdDraw.picture(0,0,"images/starfield.jpg");
            i=0;

            for (Body element : P)                       
            {
              element.draw();
            }


            
            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;

            
        }


        StdOut.printf("%d\n", P.length);
        StdOut.printf("%.2e\n", radius);

        for (int j = 0; j < P.length; j++) 
        {
                 StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",P[j].xxPos, P[j].yyPos, P[j].xxVel,P[j].yyVel, P[j].mass, P[j].imgFileName);   
        }
        
       
    }



    public static Body[] readBodies(String fName)
    {

      int planets_num=countPlanets(fName);


        String []  carrier;
        Body [] p = new Body[planets_num];
        In in = new In(fName);
        carrier = in.readAllStrings();
        
        for (int i = 2; i < 2+planets_num; i++) 
         { 
           p[i-2]= new Body (Double.parseDouble(carrier [6*i-10]), Double.parseDouble(carrier [6*i-9]), Double.parseDouble(carrier[6*i-8]), Double.parseDouble(carrier[6*i-7]),Double.parseDouble(carrier[6*i-6]),carrier[6*i-5]);
         }
        return p;
    }
    
    public static double readRadius(String fName)
    {
                 In in = new In(fName);
                 double radius=0; 
                 for (int i = 0; i < 2; i++) 
                 {
                     radius = in.readDouble();
                 }
                return radius;
    }

    private static int countPlanets(String fName)
    {
                 In in = new In(fName);
                 int count= in.readInt();
                return count;
    }
    
}