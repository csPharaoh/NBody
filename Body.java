public class Body
{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    
    public Body(double xP, double yP, double xV,double yV, double m, String img)
    {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body p)
    {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    
    public double calcDistance(Body p)
    {
    	double xxDiff = this.xxPos - p.xxPos;
    	double yyDiff = this.yyPos - p.yyPos;

    	return Math.pow((xxDiff*xxDiff + yyDiff*yyDiff),.5) ;
        
    }
    
    
    public double calcForceExertedBy(Body p)
    {
    	double distance = this.calcDistance(p);
        return ((6.67e-11)* p.mass * this.mass)/(distance * distance);
    }
    
    public double calcForceExertedByX(Body p)
    {
       
        return this.calcForceExertedBy(p)*((p.xxPos-this.xxPos)/calcDistance(p));
    }
    
    public double calcForceExertedByY(Body p)
    {
       
        return this.calcForceExertedBy(p)*((p.yyPos-this.yyPos)/calcDistance(p));
    }
    
    public double calcNetForceExertedByX (Body [] p)
    {
        double result = 0;
        for (Body element : p) 
        {
           if (!element.equals(this)) 
              result = result + calcForceExertedByX (element);
        }
        return result;
    }
    
    public double calcNetForceExertedByY (Body[] p)
    {
        double result = 0;
        for (Body element : p) 
        {
           if (!element.equals(this)) 
              result = result + calcForceExertedByY (element);
        }
        return result;  
    } 
    
    public void update(double dt,double fX,double fY)
    {
       double a_x,a_y; 
       
       a_x=fX/this.mass;
       a_y=fY/this.mass;
       
       this.xxVel=this.xxVel+a_x * dt;
       this.yyVel=this.yyVel+a_y* dt;
       
       this.xxPos = this.xxPos + this.xxVel * dt;
       this.yyPos = this.yyPos + this.yyVel* dt;
       
    }
    
    public void draw()                                                               
    {
        double univ_rad=NBody.readRadius("data/planets.txt");
        
            
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
        
    }
      
}
