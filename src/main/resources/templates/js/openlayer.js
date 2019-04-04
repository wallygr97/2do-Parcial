let map = new ol.Map({
    target: 'map',
    layers: [
        new ol.layer.Tile({
            source: new ol.source.OSM()
        }),
    ],
    view: new ol.View({
        center: ol.proj.fromLonLat([0, 0]),
        zoom: 15
    }),

});

let marker = new ol.Feature({
    geometry: new ol.geom.Point(
        ol.proj.fromLonLat([position.coords.longitude, position.coords.latitude])
    )
});

let vectorSource = new ol.source.Vector({
    features: [marker]
});

let markerVectorLayer = new ol.layer.Vector({
    source: vectorSource,
});
map.addLayer(markerVectorLayer);