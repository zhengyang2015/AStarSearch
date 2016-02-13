/**
 * Created by YangYu on 2/8/16.
 */

// event trigger
//drag panel
$('.panel').draggable();

// clear path
$('#reset').click(function() {
    route.selectAll("*").remove();
});

// start drawing path
$('#start').click(function() {
    drawPath(data);
});

// hide welcome
$('#begin').click(function () {
    $('.overlay').hide();
});

// initialize
var grid = d3.select("#grid").append("svg");
var route = d3.select("#grid").append("svg");

initGrid();
fill(10, 9, "rgba(111, 190, 255, 0.6)");
fill(10, 11, "rgba(111, 190, 255, 0.6)");
fill(9, 10, "rgba(111, 190, 255, 0.6)");
//fill(11, 10, "rgba(20, 20, 255, 0.8)");
//fill(12, 10, "rgba(20, 20, 255, 0.8)");
//fill(12, 9, "rgba(20, 20, 255, 0.8)");
//fill(12, 8, "rgba(20, 20, 255, 0.8)");
//fill(12, 7, "rgba(20, 20, 255, 0.8)");
//fill(12, 6, "rgba(20, 20, 255, 0.8)");
//fill(13, 6, "rgba(20, 20, 255, 0.8)");
//fill(14, 6, "rgba(20, 20, 255, 0.8)");
//fill(15, 6, "rgba(20, 20, 255, 0.8)");
//fill(16, 6, "rgba(20, 20, 255, 0.8)");
//fill(17, 6, "rgba(20, 20, 255, 0.8)");
//fill(17, 7, "rgba(20, 20, 255, 0.8)");
//fill(17, 8, "rgba(20, 20, 255, 0.8)");
//fill(17, 9, "rgba(20, 20, 255, 0.8)");
//fill(17, 10, "rgba(20, 20, 255, 0.8)");
//fill(18, 10, "rgba(20, 20, 255, 0.8)");
//fill(19, 10, "rgba(20, 20, 255, 0.8)");

fill(10, 10, "rgba(0, 255, 0, 0.6)");
fill(20, 10, "rgba(255, 0, 0, 0.6)");

fill(15, 8, "rgba(124, 124, 124, 0.6)");
fill(15, 9, "rgba(124, 124, 124, 0.6)");
fill(15, 10, "rgba(124, 124, 124, 0.6)");
fill(15, 11, "rgba(124, 124, 124, 0.6)");
fill(15, 12, "rgba(124, 124, 124, 0.6)");

var data = [
    [315, 315],
    [375, 315],
    [375, 195],
    [525, 195],
    [525, 315],
    [555, 315],
    [585, 315],
    [615, 315]
];
drawPath(data);

// draw grid
function initGrid() {
    var width = 3030;
    var height = 3030;

    var numGrids = 101;
    var arr = d3.range(0, numGrids + 1);
    var gridSize = width / numGrids;

    var gridEnter = grid.selectAll("line").data(arr).enter();

    gridEnter.append("line")
        .attr("x1", function (d) {
            return d * gridSize
        })
        .attr("x2", function (d) {
            return d * gridSize;
        })
        .attr("y1", 0)
        .attr("y2", height)
        .style("stroke", "rgba(212, 212, 212, 1)");

    gridEnter.append("line")
        .attr("x1", 0)
        .attr("x2", width)
        .attr("y1", function (d) {
            return d * gridSize
        })
        .attr("y2", function (d) {
            return d * gridSize
        })
        .style("stroke", "rgba(212, 212, 212, 1)");
}

// fill color in one node
function fill(x, y, color) {
    grid.append("rect")
        .attr("x", x*30)
        .attr("y", y*30)
        .attr("width", 30)
        .attr("height", 30)
        .style("fill", color);
}

// draw path between origin and destination
function drawPath(points) {

    var line = d3.svg.line()
        .interpolate("linear");
        //.x(function(d) {return d.x;})
        //.y(function(d) {return d.y;});

    var path = route.append("path")
        .data([points])
        .attr('d', line(points))
        .attr("fill", "none")
        .attr('stroke', 'rgba(255, 166, 0, 1)');
    /*
    var totalLength = path.node().getTotalLength();

    path.attr("stroke-dasharray", totalLength + " " + totalLength)
        .attr("stroke-dashoffset", totalLength)
        .transition()
        .duration(2000)
        .attr("stroke-dashoffset", 0);
        */
}

