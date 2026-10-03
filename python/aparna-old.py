#!/usr/bin/python3

import time
import re

#process_dict = {}
process_dict = {'components': [{'name': 'nvme_comp1', 'namespace-count': 1, 'performance': {'storage-service': [{'name': 'value'}]}, 'subsystem': {'name': ['subsys1'], 'uuid': '', 'hosts': [{'nqn': 'nqn.1995-08.com.example13'}], 'os-type': ['linux']}, 'total-size': 100000000}, {'name': 'nvme_comp2', 'namespace-count': 2, 'performance': {'storage-service': [{'name': 'extreme'}]}, 'subsystem': {'name': ['subsys2'], 'uuid': '', 'hosts': [{'nqn': 'nqn.1995-08.com.example456'}], 'os-type': ['vmware']}, 'total-size': 100000000}], 'os-type': 'windows', 'rpo': {'local': {'name': ['hourly']}}}
#process_dict = {'components': [{'name': 'nvme_comp1', 'namespace-count': 1, 'performance': {'storage-service': [{'name': 'value'}]}, 'subsystem': {'name': ['subsys1'], 'uuid': '', 'hosts': [{'nqn': 'nqn.1995-08.com.example13'}], 'os-type': ['linux']}, 'total-size': 100000000}, {'name': 'nvme_comp2', 'namespace-count': 2, 'performance': {'storage-service': [{'name': 'extreme'}]}, 'subsystem': {'name': ['subsys2'], 'uuid': '', 'hosts': [{'nqn': 'nqn.1995-08.com.example456'}], 'os-type': ['vmware']}, 'total-size': 100000000}]}


#entry = 'parameters.rpo.local.name'
entry = 'parameters.components.performance.storage-service'
entry_list = entry.split('.')
# throw away 'parameters'
entry_list.pop(0)

def process_dont_treat_as_array(process_dict, entry_list, index):
    '''Method to de-list certain values in the JSON dict'''

    log_str = "process_dont_treat_as_array"

    # this contains the keys to search for
    key_list = entry_list

    # this contains the first key to search for
    #array_entry = entry_list.pop(0)
    array_entry = entry_list[index]

    # search for that key in the json
    #my_return = ""
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
                for elem in val:
                    # do we have a dictionary in the list?
                    #if (isinstance(elem, dict) and len(entry_list) > 0):
                    if (isinstance(elem, dict) and len(entry_list)-1 > index):
                        print("entry_list = ", entry_list)
                        process_dont_treat_as_array(elem, entry_list, index + 1)
                    else:
                        print("setting final value = ", elem)
                        process_dict[key] = elem
                        #my_return = elem
                        #break
                #print("my_return = ", my_return)
                #if my_return != "":
                #    break
    #return my_return


print("entry_list = ", entry_list)
#my_value = process_dont_treat_as_array(process_dict, entry_list)
process_dont_treat_as_array(process_dict, entry_list, 0)
print("process_dict = ", process_dict)
