#!/usr/bin/env ruby

require 'csv'

def deinit_all()
  
  CSV.foreach("repos.rcsv", :headers => true) do |csv_obj|
  
    deinit(csv_obj['Repo'])
    
  end
  
  system("rm -rf .git/modules/")
  
end


def deinit(repo)
  system("sudo rm -rf #{repo}")
  system("git rm -r --cached #{repo}")
  system("git submodule deinit #{repo}")
  system("rm -rf .git/modules/#{repo}")
end


if ARGV.size() == 1
  repo = ARGV[0]
  deinit(repo)
elsif ARGV.size() == 0
  deinit_all()
end


