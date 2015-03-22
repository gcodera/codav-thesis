
#iterate from user 1 to 6
for user in range(1,7):
  print "Processing data from User", user 
  
  #process each line from <user>.csv
  for line in open(str(user) + ".csv", 'r'):
    
    #line exploded by semicolon; parses date, fields, and values, 
    line_exbysemi = line[:-1].split(';')

    #default value of timestamp
    timestamp = "invalid date"

    #string to be printed out
    line_out = ""

    #PARSE PER FIELD
    if line_exbysemi[3] == "airplane":
      line_out = line_exbysemi[4]
      # print line_out


    elif "app|" in line_exbysemi[3]:
      substr_exbybar = line_exbysemi[3].split('|')
      # print substr_exbybar

      #pid
      if substr_exbybar[1].isdigit():
        if substr_exbybar[2] == "importance":
          # print line_exbysemi[4]
          pass #output to importance
        if substr_exbybar[2] == "name":
          # print line_exbysemi[4]
          pass
        if substr_exbybar[2] == "starttime":
          # print line_exbysemi[4]
          pass

      #recent
      if "recent" in line_exbysemi[3]:
        substr_exbybar = line_exbysemi[3].split('|')

        print substr_exbybar[2], line_exbysemi[4]

        # pass
        # 138394;42017750;2013-01-27T01:03:55.592+0000;app|recent|0;com.cyanogenmod.trebuchet/.Launcher

    elif "audio|" in line_exbysemi[3]:
      substr_exbybar = line_exbysemi[3].split('|')
      
      if "ringermode" == substr_exbybar[1]:
        # print line_exbysemi[4]
        pass
      if "volume" == substr_exbybar[1] or "maxvolume" == substr_exbybar[1]:
        # store in (substr_exbybar[2] + ".csv") line_exbysemi[4]
        pass

    # elif line_exbysemi[3] == "conn":
    #   pass

    elif "hf|" in line_exbysemi[3]:
      substr_exbybar = line_exbysemi[3].split('|')

      if "app" == substr_exbybar[1]:
        # print line_exbysemi[4]
        pass
      if "locked" == substr_exbybar[1]:
        # print line_exbysemi[4]
        pass

    # elif "memorycard|" in line_exbysemi[3]: 
    #   substr_exbybar = line_exbysemi[3].split('|')
    #   pass


    elif "phone" in line_exbysemi[3]:
      substr_exbybar = line_exbysemi[3].split('|')

      if "activeoperator" in line_exbysemi[3]:
        # print line_exbysemi[4]
        pass
      if "roaming" in line_exbysemi[3]:
        # print line_exbysemi[4]
        pass
      if "servicestate" in line_exbysemi[3]:
        # print line_exbysemi[4]
        pass
      if "celllocation" in line_exbysemi[3]:
        # store into substr_exbybar[2] line_exbysemi[4]
        # print line_exbysemi[4]
        pass
      if "signal" in line_exbysemi[3]:
        # store into substr_exbybar[2] line_exbysemi[4]
        pass
      if "sim" in line_exbysemi[3]:
        # store into substr_exbybar[2] line_exbysemi[4]
        pass

    elif "power" in line_exbysemi[3]:
      substr_exbybar = line_exbysemi[3].split('|')

      if "battery" == substr_exbybar[1]:
        # store into substr_exbybar[2] line_exbysemi[4]
        pass
      if "charger" == substr_exbybar[1]:
        pass
        #store line_exbysemi[4] into charger.csv

    elif "screen" in line_exbysemi[3]:
      pass
      substr_exbybar = line_exbysemi[3].split('|')
      if "brightness" == substr_exbybar[1]:
        # store into substr_exbybar[2] line_exbysemi[4]
        pass
      if "power" == substr_exbybar[1]:
      # store into substr_exbybar[2] line_exbysemi[4]
        pass
      
    # elif line_exbysemi[3] == "sms":
    #   pass

    elif "wifi" in line_exbysemi[3]:
      pass
      substr_exbybar = line_exbysemi[3].split('|')

      if "connected" == substr_exbybar[1]:
        pass
        # store substr_exbybar[2] (bssid), substr_exbybar[3] (field), line_exbysemi[4](value)
      if "scancomplete" == substr_exbybar[1]:
        pass
        # store line_exbysemi[4]
      if "scan" == substr_exbybar[1]:

      if "state" == substr_exbybar[1]:
        pass
        # store <value> in state.csv


# ------------------
    if "invalid" not in line:
      timestamp = line_exbysemi[2].split('T')
      day = timestamp[0].split('-')
      time = timestamp[1].split('.')[0].split(':')

