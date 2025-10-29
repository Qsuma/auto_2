import 'package:flutter/material.dart';
import '../native_channels.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _status = 'unknown';

  Future<void> _fetchStatus() async {
    final res = await NativeChannels.getCarStatus();
    setState(() {
      _status = res ?? 'no response';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Auto Demo - Home')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text('Car status: $_status'),
            const SizedBox(height: 12),
            ElevatedButton(
              onPressed: _fetchStatus,
              child: const Text('Obtener estado nativo'),
            ),
            const SizedBox(height: 12),
            ElevatedButton(
              onPressed: () => Navigator.pushNamed(context, '/second'),
              child: const Text('Ir a navegación'),
            ),
          ],
        ),
      ),
    );
  }
}
