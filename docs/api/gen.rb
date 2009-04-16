#!/usr/bin/env ruby
require 'yaml'
require 'fileutils'

FileUtils.cd(File.dirname(__FILE__))

data = {}
ARGV.each {|arg| data[arg] = YAML.load_file("#{arg}.yml")}

FileUtils.cd('../..')
root = FileUtils.pwd

data.each do |folder, files|
	FileUtils.cd(root)
	FileUtils.mkdir_p(folder)
	FileUtils.cd(folder)
	files.each do |name, info|
		File.open("#{name}.java", 'w+') do |file|
			file.puts info['docs'].split("\n").map{|s| "// #{s}"}.join("\n")
			file.print "public #{info['type']} #{name}"
			file.print " extends #{info['extends']}" if info['extends']
			file.print " implements #{info['implements'].join(', ')}" if info['implements']
			file.puts " {"
			file.puts info['values'].map{|v| "\t#{v}"}.join(",\n") << ";" rescue nil
			info['attributes'].each do |name, docs|
				file.puts docs.split("\n").map{|s| "\t// #{s}"}.join("\n")
				file.puts "\t#{name};"
			end rescue nil
			file.puts ""
			info['methods'].each do |name, docs|
				file.puts docs.split("\n").map{|s| "\t// #{s}"}.join("\n")
				unless info['type'] == 'interface'
					file.puts "\t#{name} {\n\t}"
				else
					file.puts "\t#{name};"
				end
				file.puts ""
			end rescue nil
			file.puts "}"
		end
	end
end
