import stormpy
import sys
import files

if __name__ == '__main__':
    model_path = sys.argv[1]

    last_occurrence = model_path.rfind('/')
    file_name = model_path[last_occurrence + 1:]
    if last_occurrence == -1:
        folder_path = ''
    else:
        folder_path = model_path[:last_occurrence]
    path = files._path(folder_path, file_name)
    print("Model file is located at: " + path)

    initial_prism_program = stormpy.parse_prism_program(path)
    initial_model = stormpy.build_model(initial_prism_program)

    initial_prism_program = stormpy.parse_prism_program(path)
    initial_model = stormpy.build_model(initial_prism_program)
    print("Total number of states: " + str(len(initial_model.states)))

