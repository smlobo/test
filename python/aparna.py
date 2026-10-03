#!/usr/bin/python3

import time
import re

process_dict = {'components': [{'name': 'nvme_comp1', 'namespace-count': 1, 'performance': {'storage-service': [{'name': 'value'}]}, 'subsystem': {'name': ['subsys1'], 'uuid': '', 'hosts': [{'nqn': 'nqn.1995-08.com.example13'}], 'os-type': ['linux']}, 'total-size': 100000000}, {'name': 'nvme_comp2', 'namespace-count': 2, 'performance': {'storage-service': [{'name': 'extreme'}]}, 'subsystem': {'name': ['subsys2'], 'uuid': '', 'hosts': [{'nqn': 'nqn.1995-08.com.example456'}], 'os-type': ['vmware']}, 'total-size': 100000000}]}

entry = 'components.performance.storage-service'
entry_list = entry.split('.')

def process_dont_treat_as_array(process_dict, entry_list, index):
    if len(entry_list) == index:
        return

    array_entry = entry_list[index]

    for key, val in process_dict.items():
        print("key, val in process_dict = {} ^^ {}".format(key, val))

        if key == array_entry:
            print("key matches array_entry = ", key)

            # check if the the value is a dictionary
            if isinstance(val, dict):
                print("val is dictionary: ", val)
                process_dont_treat_as_array(val, entry_list, index + 1)

            # check if the value is a list
            if isinstance(val, list):
                print("val is list: ", val)

                if len(entry_list) - 1 == index:
                    if len(val) == 1:
                        print("setting final value = ", val[0])
                        process_dict[key] = val[0]

                else:
                    for elem in val:
                        print("elem in list: ", elem)
                        # do we have a dictionary in the list?
                        #if (isinstance(elem, dict) and len(entry_list) > 0):
                        if isinstance(elem, dict):
                            print("entry_list = ", entry_list)
                            process_dont_treat_as_array(elem, entry_list, index + 1)

print("entry_list = ", entry_list)
process_dont_treat_as_array(process_dict, entry_list, 0)
print("process_dict = ", process_dict)
