# Trouble shooting

- https://stackoverflow.com/questions/50609417/elasticsearch-error-cluster-block-exception-forbidden-12-index-read-only-all
- https://www.elastic.co/guide/en/elasticsearch/reference/master/fix-watermark-errors.html

```

curl -X GET http://localhost:9200/_cluster/settings?pretty

{
  "persistent": {
    "cluster.routing.allocation.disk.watermark.low": "90%",
    "cluster.routing.allocation.disk.watermark.high": "95%",
    "cluster.routing.allocation.disk.watermark.flood_stage": "97%"
  }
}

curl -X PUT "localhost:9200/_cluster/settings?pretty" -H "Content-Type: application/json" -d @elastic-settings.txt

{
  "index.blocks.read_only_allow_delete": null
}

curl -X PUT "localhost:9200/*/_settings?expand_wildcards=all" -H "Content-Type: application/json" -d @elastic-settings2.txt

```