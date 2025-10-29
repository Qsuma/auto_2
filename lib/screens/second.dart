import 'package:flutter/material.dart';
import '../native_channels.dart';

class SecondScreen extends StatefulWidget {
  const SecondScreen({super.key});

  @override
  State<SecondScreen> createState() => _SecondScreenState();
}

class _SecondScreenState extends State<SecondScreen> {
  String _navResult = 'idle';
  final TextEditingController _ctrl = TextEditingController(text: 'Destino ejemplo');

  Future<void> _startNav() async {
    final destination = _ctrl.text;
    final res = await NativeChannels.startNavigation(destination);
    setState(() {
      _navResult = res ?? 'no response';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Auto Demo - Navegación')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(controller: _ctrl),
            const SizedBox(height: 12),
            ElevatedButton(onPressed: _startNav, child: const Text('Iniciar navegación (nativo)')),
            const SizedBox(height: 12),
            Text('Resultado: $_navResult'),
          ],
        ),
      ),
    );
  }
}
