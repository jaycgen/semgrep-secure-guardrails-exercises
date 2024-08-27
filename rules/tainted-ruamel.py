# before 0.15.0
from ruamel.yaml import YAML

# after 0.15.0
from ruamel import yaml

import ruamel

from http.server import BaseHTTPRequestHandler
import urllib.parse

class GetHandler(BaseHTTPRequestHandler):

    def do_GET(self):
		
        tainted = urlparse.urlparse(self.path).query

        yml = ruamel.yaml.YAML(typ='unsafe')
        # ruleid: tainted-ruamel
        data = yml.load(tainted)
        # ok: tainted-ruamel
        data = yml.load(s)

        yml = ruamel.yaml.YAML(typ='safe')
        # ok: tainted-ruamel 
        data = yml.load(tainted)

        # The default loader (typ='rt') is a direct derivative of the safe loader
        yml = ruamel.yaml.YAML(typ='rt')
        # ok: tainted-ruamel 
        data = yml.load(tainted)

        yml = ruamel.yaml.YAML()
        # ok: tainted-ruamel 
        data = yml.load(tainted)
