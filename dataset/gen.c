#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
// Fast version of gen.rb

void usage(char* bin) {
	printf("Usage: %s NUM [FILENAME]\n", bin);
	puts("Generates a file (dataset.csv by default) containing NUM entries");
}

void dirname(char *string) {
	string += strlen(string);
	while (--string) {
		if (*string == '/') {
			*string = '\0';
			break;
		}
	}
}

void strip(char *string) {
	char c;
	string += strlen(string);
	while ((c = *(--string)) == '\r' || c == '\t' || c == '\n' || c == ' ') {
		*string = '\0';
	}
}

int random_num(int to) {
	return (int)((rand() / (double)RAND_MAX) * to);
}

double random_double(double to) {
	return (rand() / (double)RAND_MAX) * to;
}

struct tm * random_time(time_t start, time_t finish) {
	time_t t = (time_t)(start + random_double(difftime(finish, start)));
	return localtime(&t);
}

int main(int argc, char **argv) {
	int i, size;
	char users[65][15];
	char tracks[1443][140];
	char* output;
	char time_string[31];
	struct tm * timeinfo;
	time_t start;
	time_t finish;
	FILE* file;

	switch(argc) {
		case 1:
			usage(argv[0]);
			return 1;
		case 2:
			size = atoi(argv[1]);
			output = "dataset.csv";
			break;
		default:
			size = atoi(argv[1]);
			output = argv[2];
	}

	dirname(argv[0]);
	chdir(argv[0]);

	file = fopen("users.csv", "r");
	fgets(users[0], 15, file);
	for (i = 0; i < 65; i++) {
		fgets(users[i], 15, file);
		strip(users[i]);
	}
	fclose(file);

	file = fopen("tracks.csv", "r");
	fgets(tracks[0], 140, file);
	for (i = 0; i < 1444; i++) {
		fgets(tracks[i], 140, file);
		strip(tracks[i]);
	}
	fclose(file);

	start = time(0);
	timeinfo = localtime(&start);
	timeinfo->tm_year = 2004 - 1900;
	timeinfo->tm_mon = 4;
	timeinfo->tm_mday = 1;
	finish = mktime(timeinfo);

	file = fopen(output, "w+");
	for (i = 0; i < size; i++) {
		strftime(time_string, 31, "%a %b %d %H:%M:%S %Z %Y", random_time(start, finish));
		fprintf(file, "%s,%s,%s\n", users[random_num(65)], tracks[random_num(1443)], time_string);
	}
	fclose(file);

	return 0;
}
