package com.danparkin.shapes;

import java.awt.*;

abstract class Shape {
    private Color _color;
    public void darken() { /*..*/ }
    public void draw(Graphics g) {
        g.setColor(_color);
        render(g);
    }
    // delegate drawing responsibility to sub classes
    abstract protected void render(Graphics g);
}

class Circle extends Shape {
    private Point _center;
    private int _radius;

    @Override
    // render receives a graphics object already setup by super class
    protected void render(Graphics g) {
        // render circle geometry
    }
}

class Line extends Shape {
    private Point _beginning;
    private Point _end;

    @Override
    protected void render(Graphics g) {
        // render line geometry
    }
}
