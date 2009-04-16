#!/usr/bin/env ruby
# This is more portable than tha C version, but it's damn slow
require 'fileutils'

size = ARGV[0].to_i
output = ARGV[1] or 'dataset.csv'

FileUtils.cd(File.dirname(__FILE__))

users = File.read('users.csv').gsub(/\r\n/, "\n").split("\n")
users.delete_at(0)

tracks = File.read('tracks.csv').gsub(/\r\n/, "\n").split("\n")
tracks.delete_at(0)

START = Time.mktime(2004, 05, 01)
FINISH = Time.now

def time
	START + rand(START - FINISH)
end

File.open(output, 'w+') do |file|
	size.times do
		file.puts "#{users.choice},#{tracks.choice},#{time}"
	end
end
